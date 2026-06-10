package com.academy.course.service;

import com.academy.course.dao.employeeDao.EmployeeDAO;
import com.academy.course.dao.employeeDao.EmployeeDAOImpl;
import com.academy.course.dao.orderDao.OrderDAO;
import com.academy.course.dao.orderDao.OrderDAOImpl;
import com.academy.course.dto.EmployeeDTO;
import com.academy.course.dto.OrderDTO;
import com.academy.course.mapper.*;
import com.academy.course.model.Employee;
import com.academy.course.model.Order;

import com.academy.course.utils.Role;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.*;

public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger logger = LogManager.getLogger(EmployeeServiceImpl.class);
    private final EmployeeDAO employeeDAO = new EmployeeDAOImpl();
    private final OrderDAO orderDAO = new OrderDAOImpl();
    private final BaseEmployeeValidator baseEmployeeValidator = new BaseEmployeeValidatorImpl();
    private final EmployeeMapper employeeMapper = MapperFactory.getEmployeeMapper();
    private final OrderMapper orderMapper = MapperFactory.getOrderMapper();
    private final BusinessEmployeeValidator businessEmployeeValidator = new BusinessEmployeeValidatorImpl(baseEmployeeValidator,employeeDAO);
    private final IdValidatorFactory factory;

    public EmployeeServiceImpl(IdValidatorFactory factory) {
        this.factory = factory;
    }

    @Override
    public void deleteOrderOfEmployee(EmployeeDTO employeeDTO, OrderDTO orderDTO) throws SQLException {
        factory.getEmployeeValidator().validateId(employeeDTO.getId());
        Employee employee = employeeDAO.get(employeeDTO.getId());
        employee.getOrders().removeIf(order1 -> order1.getId().equals(orderDTO.getId()));
        employeeDAO.update(employee);
    }


    @Override
    public Set<EmployeeDTO> getAllEmployeesWithOrdersAndItems() {
        return employeeMapper.mapToSetDTOS(employeeDAO.getAllEmployees());
    }

    @Override
    public EmployeeDTO findEmployeeById(Integer id) throws SQLException {
        factory.getEmployeeValidator().validateId(id);
        logger.info("Employee with id {} founded",id);
        return employeeMapper.mapToDTO(employeeDAO.get(id));
    }

    @Override
    public EmployeeDTO findEmployeeByLogin(String login) {
        businessEmployeeValidator.findByLoginValidation(login);
        logger.info("Employee with login {} founded",login);
        return employeeMapper.mapToDTO(employeeDAO.getEmployeeByLogin(login));
    }

    @Override
    public void addNewOrderToEmployee(EmployeeDTO employeeDTO) throws SQLException {
        factory.getEmployeeValidator().validateId(employeeDTO.getId());
        Employee employee = employeeDAO.get(employeeDTO.getId());
       baseEmployeeValidator.addNewOrderValidator(employeeDTO);
        Order order = Order.builder()
                .isBought(false)
                .employee(employee)
                .build();
        employee.addOrder(order);
        employeeDAO.update(employee);
        logger.info("Order {} successfully created to Employee {}",order,employeeDTO);
    }

    @Override
    public boolean registerEmployee(EmployeeDTO employeeDTO, String pass, Role role) throws SQLException {

        businessEmployeeValidator.employeeRegistrationValidator(employeeDTO, pass, role);

        Employee employee = Employee.builder()
                .login(employeeDTO.getLogin())
                .role(role)
                .passWord(PasswordHasher.hashPass(pass))
                .build();

        Order order = Order.builder()
                .employee(employee)
                .isBought(false)
                .build();

        employee.addOrder(order);

        employeeDAO.save(employee);

        logger.info("Employee {} successfully registered",employeeDTO);
        return true;

    }

    @Override
    public void updateEmployee(Integer oldValueId, EmployeeDTO newValue) throws SQLException {
        factory.getEmployeeValidator().validateId(oldValueId);
        Employee employee = employeeDAO.get(oldValueId);
        baseEmployeeValidator.updatingValidation(newValue);
        employee.setLogin(newValue.getLogin());
        employee.setRole(newValue.getRole());
        employeeDAO.update(employee);
        logger.info("Employee with id {} successfully updated",oldValueId);
    }

    @Override
    public void deleteEmployee(Integer employeeId) throws SQLException {
        factory.getEmployeeValidator().validateId(employeeId);
        employeeDAO.delete(employeeDAO.get(employeeId));
        logger.info("Employee with id {} successfully deleted",employeeId);
    }


    @Override
    public boolean login(String login, String passWord) throws NoSuchFieldException, SQLException {
        businessEmployeeValidator.tryToLoginValidation(login,passWord);
        logger.info("Login {} successfully authorized",login);
        return true;
    }

}
