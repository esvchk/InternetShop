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
import com.academy.course.model.Order;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.NoResultException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CustomerServiceImpl implements CustomerService {

    private static final Logger logger = LogManager.getLogger(CustomerServiceImpl.class);
    private final CustomerDAO customerDAO = new CustomerDAOImpl();
    private final OrderDAO orderDAO = new OrderDAOImpl();
    private final OrderMapper orderMapper = MapperFactory.getOrderMapper();
    private final CustomerMapper customerMapper = MapperFactory.getCustomerMapper();
    private final CustomerWithOrdersMapper customerWithOrdersMapper = MapperFactory.getCustomerWithOrdersMapper();



    @Override
    public void deleteOrderOfCustomer(CustomerDTO customerDTO, OrderDTO orderDTO) throws SQLException {
        customerDAO.deleteOrderOfCustomer(customerMapper.mapToEntity(customerDTO),
                orderMapper.mapToEntity(orderDTO));
    }

    @Override
    public Set<CustomerDTO> getAllCustomers() {
        return customerMapper.mapToListDTOS(customerDAO.getAllCustomers());
    }

    @Override
    public Set<OrderDTO> getAllOrdersOfCustomer(CustomerDTO customerDTO) throws SQLException {
        Customer customer = customerDAO.get(customerDTO.getId());
        Set<Order> orders = customer.getOrders();
        return orderMapper.mapToListDTOS(orders);
    }

    @Override
    public void buyOrder(CustomerDTO customerDTO,OrderDTO orderDTO) throws SQLException {
        orderDAO.buyOrder(orderDTO.getId(),customerMapper.mapToEntity(customerDTO));
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
            logger.warn("Not found {}",login);
        return null;
    }

    @Override
    public void createCustomer(CustomerDTO customerDTO) throws SQLException {
        customerDAO.save(customerMapper.mapToEntity(customerDTO));
    }

    @Override
    public void updateCustomer(CustomerDTO customerDTO, String newPassword) throws SQLException {
        Customer customer = customerDAO.get(customerDTO.getId());
        customer.setLogin(customerDTO.getLogin());
        customer.setEmail(customerDTO.getEmail());
        customer.setPassWord(newPassword);
        customerDAO.update(customer);

    }

    @Override
    public void deleteCustomer(CustomerDTO customerDTO) throws SQLException {
        Customer customer = customerDAO.get(customerDTO.getId());
        customerDAO.delete(customer);
    }

    @Override
    public boolean register(String login, String passWord) {
        try {
            if (customerDAO.getCustomerByLogin(login) != null) {
                logger.warn("already exist with login {}", login);
                throw new UserAlreadyExists("User already registered with this login");
            } else {
                Customer customer = Customer.builder()
                        .login(login)
                        .passWord(PasswordHasher.hashPass(passWord))
                        .orders(new HashSet<>())
                        .email("")
                        .build();
                customerDAO.save(customer);
            }
        }catch (NoResultException | SQLException e){
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
                    logger.info("successfully entered {}", customer);
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
