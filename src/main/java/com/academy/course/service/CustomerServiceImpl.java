package com.academy.course.service;

import com.academy.course.dao.customerDao.CustomerDAO;
import com.academy.course.dao.customerDao.CustomerDAOImpl;
import com.academy.course.dao.orderDao.OrderDAO;
import com.academy.course.dao.orderDao.OrderDAOImpl;
import com.academy.course.dto.CustomerDTO;
import com.academy.course.dto.OrderDTO;
import com.academy.course.exception.UserAlreadyExists;
import com.academy.course.exception.UserNotFound;
import com.academy.course.exception.WrongPassWord;
import com.academy.course.mapper.*;
import com.academy.course.model.Customer;
import com.academy.course.model.Item;
import com.academy.course.model.Order;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.NoResultException;
import java.sql.SQLException;
import java.util.*;

public class CustomerServiceImpl implements CustomerService {

    private static final Logger logger = LogManager.getLogger(CustomerServiceImpl.class);
    private final CustomerDAO customerDAO = new CustomerDAOImpl();
    private final OrderDAO orderDAO = new OrderDAOImpl();
    private final CustomerMapper customerMapper = MapperFactory.getCustomerMapper();


    @Override
    public void deleteOrderOfCustomer(CustomerDTO customerDTO, OrderDTO orderDTO) throws SQLException {
        Customer customer = customerDAO.get(customerDTO.getId());
        Order order = orderDAO.get(orderDTO.getId());
        customer.getOrders().removeIf(order1 -> order1.getId().equals(order.getId()));
        orderDAO.delete(order);
    }

    @Override
    public Set<CustomerDTO> getAllCustomersWithOrdersAndItems() {
        return customerMapper.mapToSetDTOS(customerDAO.getAllCustomers());
    }

    @Override
    public CustomerDTO findCustomerById(Integer id) throws SQLException {
        return customerMapper.mapToDTO(customerDAO.get(id));
    }

    @Override
    public CustomerDTO findCustomerByLogin(String login) {
        if (customerDAO.getCustomerByLogin(login) != null) {
            return customerMapper.mapToDTO(customerDAO.getCustomerByLogin(login));
        } else
            logger.warn("Not found {}", login);
        return null;
    }

    @Override
    public void addNewOrderToCustomer(CustomerDTO customerDTO) throws SQLException {
        Customer customer = customerDAO.get(customerDTO.getId());
        Order order = Order.builder()
                .customer(customer)
                .isBought(false)
                .build();
        customer.addOrder(order);
        customerDAO.update(customer);
    }

    @Override
    public void createCustomer(CustomerDTO customerDTO, String pass) throws SQLException {

        Customer customer = Customer.builder()
                .login(customerDTO.getLogin())
                .email(customerDTO.getEmail())
                .passWord(PasswordHasher.hashPass(pass))
                .build();

        Order order = Order.builder()
                .customer(customer)
                .isBought(false)
                .build();

        customer.addOrder(order);

        customerDAO.save(customer);

    }

    @Override
    public void updateCustomer(Integer oldValueId, CustomerDTO newValue) throws SQLException {
        Customer customer = customerDAO.get(oldValueId);
        customer.setLogin(newValue.getLogin());
        customer.setEmail(newValue.getEmail());
        customerDAO.update(customer);
    }

    @Override
    public void deleteCustomer(CustomerDTO customerDTO) throws SQLException {
        if (customerDTO.getId() != null) {
            customerDAO.delete(customerDAO.get(customerDTO.getId()));
        } else
            throw new NullPointerException();

    }

    @Override
    public boolean register(CustomerDTO customerDTO, String password) throws SQLException {
        String login = customerDTO.getLogin();
        try {
            if (customerDAO.getCustomerByLogin(login) != null) {
                logger.warn("already exist with login {}", login);
                throw new UserAlreadyExists("User already registered with this login");
            } else {
                createCustomer(customerDTO,password);
            }
        } catch (NoResultException | SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean login(String login, String passWord) throws NoSuchFieldException, SQLException, UserNotFound {
        try {
            Customer customer = customerDAO.getCustomerByLogin(login);
            if (customer.getLogin().equals(login)) {
                if (PasswordHasher.checkPass(passWord, customer.getPassWord())) {
                    logger.info("successfully loged in{}", customer);
                    return true;
                } else {
                    logger.warn("Wrong passWord {} by login {}", passWord, login);
                    throw new WrongPassWord("Wrong Password");
                }
            }
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

}
