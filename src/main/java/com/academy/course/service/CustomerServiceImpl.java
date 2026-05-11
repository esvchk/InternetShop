package com.academy.course.service;

import com.academy.course.dao.customerDao.CustomerDAO;
import com.academy.course.dao.customerDao.CustomerDAOImpl;
import com.academy.course.dao.orderDao.OrderDAO;
import com.academy.course.dao.orderDao.OrderDAOImpl;
import com.academy.course.dto.CustomerDTO;
import com.academy.course.dto.OrderDTO;
import com.academy.course.exception.UserNotFound;
import com.academy.course.mapper.*;
import com.academy.course.model.Customer;
import com.academy.course.model.Order;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

public class CustomerServiceImpl implements CustomerService {

    private static final Logger logger = LogManager.getLogger(CustomerServiceImpl.class);
    private final CustomerDAO customerDAO = new CustomerDAOImpl();
    private final OrderDAO orderDAO = new OrderDAOImpl();
    private final OrderMapper orderMapper = MapperFactory.getOrderMapper();
    private final CustomerMapper customerMapper = MapperFactory.getCustomerMapper();
    private final CustomerWithOrdersMapper customerWithOrdersMapper = MapperFactory.getCustomerWithOrdersMapper();



    @Override
    public void deleteOrder(CustomerDTO customerDTO, OrderDTO orderDTO) throws SQLException {
        customerDAO.deleteOrder(customerMapper.mapToEntity(customerDTO),
                orderMapper.mapToEntity(orderDTO));
    }

    @Override
    public void createOrder(CustomerDTO customerDTO, OrderDTO orderDTO) throws SQLException {
        customerDAO.createOrder(customerMapper.mapToEntity(customerDTO),
                orderMapper.mapToEntity(orderDTO));
    }


    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerMapper.mapToListDTOS(customerDAO.getAllCustomers());
    }

    @Override
    public List<OrderDTO> getAllOrdersOfCustomer(CustomerDTO customerDTO) throws SQLException {
        Customer customer = customerDAO.get(customerDTO.getId());
        List<Order> orders = customer.getOrders();
        return orderMapper.mapToListDTOS(orders);
    }

    @Override
    public void buyOrder(CustomerDTO customerDTO,OrderDTO orderDTO) throws SQLException {
        Order order = orderDAO.get(orderDTO.getId());
        if (order != null) {
            order.setIsBought(true);
        } else
            throw new NullPointerException();
        orderDAO.save(order);
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
    public void register(String login, String passWord) throws SQLException, UserNotFound {
        CustomerDTO existingCustomer = findCustomerByLogin(login);
        if (existingCustomer != null) {
            logger.warn("Login {} already exist",login);
        } else {
            Customer customer = Customer.builder()
                    .login(login)
                    .passWord(PasswordHasher.hashPass(passWord))
                    .build();
            customerDAO.save(customer);
        }

    }

    @Override
    public void login(String login, String passWord) throws NoSuchFieldException, SQLException, UserNotFound {
        Customer customer = customerDAO.getCustomerByLogin(login);
        String hashedPass = customer.getPassWord();
        if (!PasswordHasher.checkPass(passWord, hashedPass)) {
            throw new RuntimeException("wrong pass");
        }
    }

}
