package com.academy.course.service;

import com.academy.course.dao.customerDao.CustomerDAO;
import com.academy.course.dao.customerDao.CustomerDAOImpl;
import com.academy.course.dto.CustomerDTO;
import com.academy.course.dto.OrderDTO;
import com.academy.course.model.Customer;
import com.academy.course.utils.ObjectMapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CustomerServiceImpl implements CustomerService, ObjectMapper<Customer,CustomerDTO> {

    private final CustomerDAO customerDAO = new CustomerDAOImpl();
    private final OrderServiceImpl orderService = new OrderServiceImpl();


    @Override
    public void createOrder(CustomerDTO customerDTO, OrderDTO orderDTO) throws SQLException {
        customerDAO.createOrder(this.mapToEntity(customerDTO),orderService.mapToEntity(orderDTO));
    }

    @Override
    public void deleteOrder(CustomerDTO customerDTO, OrderDTO orderDTO) throws SQLException {
        customerDAO.deleteOrder(this.mapToEntity(customerDTO),orderService.mapToEntity(orderDTO));
    }


    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerDAO.getAllCustomers().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Set<OrderDTO> getAllOrdersOfCustomer(CustomerDTO customerDTO) {
        return customerDAO.getAllOrdersOfCustomer(this.mapToEntity(customerDTO)).stream()
                .map(orderService::mapToDTO)
                .collect(Collectors.toSet());
    }

    @Override
    public void buyOrder(CustomerDTO customerDTO, OrderDTO orderDTO) {
        OrderDTO orderToPurchase = this.mapToEntity(customerDTO).getOrders().stream().map(orderService::mapToDTO)
                .filter(orderDTO1 -> orderDTO1.equals(orderDTO))
                .findFirst().orElse(null);
        if (orderToPurchase != null) {
            orderDTO.setBought(true);
        } else
            orderDTO.setBought(false);
    }

    @Override
    public CustomerDTO findCustomerById(Integer id) throws SQLException {
        return mapToDTO(customerDAO.get(id));
    }

    @Override
    public CustomerDTO findCustomerByLogin(String login) {
        return mapToDTO(customerDAO.getCustomerByLogin(login));
    }

    @Override
    public String getPassOfCustomerByLogin(String login) {
        return customerDAO.getPassByLogin(login);
    }

    @Override
    public void createCustomer(CustomerDTO customerDTO) throws SQLException {
        customerDAO.save(mapToEntity(customerDTO));
    }

    @Override
    public CustomerDTO getCustomer(Integer id) throws SQLException {
        return mapToDTO(customerDAO.get(id));
    }

    @Override
    public void updateCustomer(CustomerDTO customerDTO,String newPassword) throws SQLException {
        CustomerDTO customer = this.getCustomer(customerDTO.getId());
        customer.setPayment(customerDTO.getPayment());
        customer.setLogin(customerDTO.getLogin());
        customer.setEmail(customerDTO.getEmail());
        customer.setDateTimeOfRegistration(customerDTO.getDateTimeOfRegistration());
        Customer customer1 = mapToEntity(customer);
        customerDAO.update(customer1);

    }

    @Override
    public void deleteCustomer(CustomerDTO customerDTO) throws SQLException {
        customerDAO.delete(this.mapToEntity(customerDTO));
    }


    @Override
    public CustomerDTO mapToDTO(Customer customer) {
        return CustomerDTO.builder()
                .id(customer.getId())
                .login(customer.getLogin())
                .email(customer.getEmail())
                .payment(customer.getPayment())
                .build();
    }

    @Override
    public Customer mapToEntity(CustomerDTO customerDTO) {
        return Customer.builder()
                .login(customerDTO.getLogin())
                .passWord(customerDTO.getLogin())
                .email(customerDTO.getEmail())
                .payment(customerDTO.getPayment())
                .build();
    }
}
