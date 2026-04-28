package com.academy.course.service;

import com.academy.course.dao.customerDao.CustomerDAO;
import com.academy.course.dao.customerDao.CustomerDAOImpl;
import com.academy.course.dto.CustomerDTO;
import com.academy.course.dto.OrderDTO;
import com.academy.course.model.Customer;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CustomerServiceImpl implements CustomerService {

    private final CustomerDAO customerDAO = new CustomerDAOImpl();
    private final OrderService orderService = new OrderServiceImpl();


    @Override
    public void createOrder(CustomerDTO customerDTO, OrderDTO orderDTO) throws SQLException {
        customerDAO.createOrder(this.mapToCustomer(customerDTO),orderService.mapToOrder(orderDTO));
    }

    @Override
    public void deleteOrder(CustomerDTO customerDTO, OrderDTO orderDTO) throws SQLException {
        customerDAO.deleteOrder(this.mapToCustomer(customerDTO),orderService.mapToOrder(orderDTO));
    }


    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerDAO.getAllCustomers().stream()
                .map(this::mapToCustomerDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Set<OrderDTO> getAllOrdersOfCustomer(CustomerDTO customerDTO) {
        return customerDAO.getAllOrdersOfCustomer(this.mapToCustomer(customerDTO)).stream()
                .map(orderService::mapToOrderDTO)
                .collect(Collectors.toSet());
    }

    @Override
    public void buyOrder(CustomerDTO customerDTO, OrderDTO orderDTO) {
        OrderDTO orderToPurchase = this.mapToCustomer(customerDTO).getOrders().stream().map(orderService::mapToOrderDTO)
                .filter(orderDTO1 -> orderDTO1.equals(orderDTO))
                .findFirst().orElse(null);
        if (orderToPurchase != null) {
            orderDTO.setBought(true);
        } else
            orderDTO.setBought(false);
    }

    @Override
    public CustomerDTO findCustomerById(Integer id) throws SQLException {
        return mapToCustomerDTO(customerDAO.get(id));
    }

    @Override
    public CustomerDTO findCustomerByLogin(String login) {
        return mapToCustomerDTO(customerDAO.getCustomerByLogin(login));
    }

    @Override
    public Customer mapToCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        return Customer.builder()
                .login(customerDTO.getLogin())
                .passWord(customer.getPassWord())
                .email(customerDTO.getEmail())
                .payment(customerDTO.getPayment())
                .orders(customer.getOrders())
                .build();
    }

    @Override
    public CustomerDTO mapToCustomerDTO(Customer customer) {
        return CustomerDTO.builder()
                .id(customer.getId())
                .login(customer.getLogin())
                .email(customer.getLogin())
                .payment(customer.getPayment())
                .build();
    }


}
