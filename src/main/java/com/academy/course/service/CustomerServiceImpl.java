package com.academy.course.service;

import com.academy.course.dao.customerDao.CustomerDAO;
import com.academy.course.dao.customerDao.CustomerDAOImpl;
import com.academy.course.dto.CustomerDTO;
import com.academy.course.dto.OrderDTO;
import com.academy.course.exception.UserNotFound;
import com.academy.course.model.Customer;
import com.academy.course.utils.Mapper;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CustomerServiceImpl implements CustomerService, Mapper<Customer, CustomerDTO> {

    private final CustomerDAO customerDAO = new CustomerDAOImpl();
    private final OrderServiceImpl orderService = new OrderServiceImpl();



    @Override
    public void deleteOrder(CustomerDTO customerDTO, OrderDTO orderDTO) throws SQLException {
        customerDAO.deleteOrder(this.mapToEntity(customerDTO), orderService.mapToEntity(orderDTO));
    }


    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerDAO.getAllCustomers().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDTO> getAllOrdersOfCustomer(CustomerDTO customerDTO) {
        if (customerDAO.getAllOrdersOfCustomer(this.mapToEntity(customerDTO)) == null) {
            return Collections.emptyList();
        } else {
            return customerDAO.getAllOrdersOfCustomer(this.mapToEntity(customerDTO)).stream()
                    .map(orderService::mapToDTO)
                    .collect(Collectors.toList());
        }

    }

    @Override
    public void buyOrder(CustomerDTO customerDTO, OrderDTO orderDTO) {
        OrderDTO orderToPurchase = this.mapToEntity(customerDTO).getOrders().stream().map(orderService::mapToDTO)
                .filter(orderDTO1 -> orderDTO1.equals(orderDTO))
                .findFirst().orElse(null);
        if (orderToPurchase != null) {
            orderDTO.setIsBought(true);
        } else
            orderDTO.setIsBought(false);
    }

    @Override
    public CustomerDTO findCustomerById(Integer id) throws SQLException {
        return mapToDTO(customerDAO.get(id));
    }

    @Override
    public CustomerDTO findCustomerByLogin(String login) throws UserNotFound {
        if (customerDAO.getCustomerByLogin(login)!= null) {
            return mapToDTO(customerDAO.getCustomerByLogin(login));
        } else
            return null;
    }

    @Override
    public void createCustomer(CustomerDTO customerDTO) throws SQLException {
        customerDAO.save(mapToEntity(customerDTO));
    }

    @Override
    public void updateCustomer(CustomerDTO customerDTO, String newPassword) throws SQLException {
        Customer customer = customerDAO.get(customerDTO.getId());
        customer.setLogin(customerDTO.getLogin());
        customer.setEmail(customerDTO.getEmail());
        customerDAO.update(customer);

    }

    @Override
    public void deleteCustomer(CustomerDTO customerDTO) throws SQLException {
        customerDAO.delete(this.mapToEntity(customerDTO));
    }

    @Override
    public void register(String login, String passWord) throws SQLException, UserNotFound {
        CustomerDTO existingCustomer = findCustomerByLogin(login);
            if (existingCustomer != null) {

            } else {
                Customer customer = Customer.builder()
                        .login(login)
                        .passWord(PasswordHasher.hashPass(passWord))
                        .build();
                CustomerDTO customerDTO = mapToDTO(customer);
                customerDAO.save(customer);
            }

    }

    @Override
    public void login(String login, String passWord) throws NoSuchFieldException, SQLException, UserNotFound {
        Customer customer = customerDAO.getCustomerByLogin(login);
        String hashedPass = customer.getPassWord();
        if (!PasswordHasher.checkPass(passWord,hashedPass)) {
            throw new RuntimeException("wrong pass");
        }
    }


    @Override
    public CustomerDTO mapToDTO(Customer customer) {
        return CustomerDTO.builder()
                .id(customer.getId())
                .login(customer.getLogin())
                .email(customer.getEmail())
                .build();
    }

    @Override
    public Customer mapToEntity(CustomerDTO customerDTO) {
        return Customer.builder()
                .login(customerDTO.getLogin())
                .passWord(null)
                .email(customerDTO.getEmail())
                .build();
    }
}
