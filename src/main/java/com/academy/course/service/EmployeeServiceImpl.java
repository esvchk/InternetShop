package com.academy.course.service;

import com.academy.course.dao.employeeDao.EmployeeDAO;
import com.academy.course.dao.employeeDao.EmployeeDAOImpl;
import com.academy.course.dao.orderDao.OrderDAO;
import com.academy.course.dao.orderDao.OrderDAOImpl;
import com.academy.course.dto.EmployeeDTO;
import com.academy.course.dto.OrderDTO;
import com.academy.course.exception.UserAlreadyExists;
import com.academy.course.exception.UserNotFound;
import com.academy.course.exception.WrongPassWord;
import com.academy.course.mapper.*;
import com.academy.course.model.Employee;
import com.academy.course.model.Order;

import com.academy.course.utils.Role;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.NoResultException;
import java.sql.SQLException;
import java.util.*;

public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger logger = LogManager.getLogger(EmployeeServiceImpl.class);
    private final EmployeeDAO employeeDAO = new EmployeeDAOImpl();
    private final OrderDAO orderDAO = new OrderDAOImpl();
    private final EmployeeMapper employeeMapper = MapperFactory.getEmployeeMapper();


    @Override
    public void deleteOrderOfCustomer(EmployeeDTO employeeDTO, OrderDTO orderDTO) throws SQLException {
        Employee employee = employeeDAO.get(employeeDTO.getId());
        Order order = orderDAO.get(orderDTO.getId());
        employee.getOrders().removeIf(order1 -> order1.getId().equals(order.getId()));
        orderDAO.delete(order);
    }

    @Override
    public Set<EmployeeDTO> getAllEmployeesWithOrdersAndItems() {
        return employeeMapper.mapToSetDTOS(employeeDAO.getAllEmployees());
    }

    @Override
    public EmployeeDTO findEmployeeById(Integer id) throws SQLException {
        return employeeMapper.mapToDTO(employeeDAO.get(id));
    }

    @Override
    public EmployeeDTO findEmployeeByLogin(String login) {
        if (employeeDAO.getEmployeeByLogin(login) != null) {
            return employeeMapper.mapToDTO(employeeDAO.getEmployeeByLogin(login));
        } else
            logger.warn("Not found {}", login);
        return null;
    }

    @Override
    public void addNewOrderToEmployee(EmployeeDTO employeeDTO) throws SQLException {
        Employee employee = employeeDAO.get(employeeDTO.getId());
        Order order = Order.builder()
                .employee(employee)
                .isBought(false)
                .build();
        employee.addOrder(order);
        employeeDAO.update(employee);
    }

    @Override
    public void createEmployee(EmployeeDTO employeeDTO, String pass, Role role) throws SQLException {

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

    }

    @Override
    public void updateEmployee(Integer oldValueId, EmployeeDTO newValue) throws SQLException {
        Employee employee = employeeDAO.get(oldValueId);
        employee.setLogin(newValue.getLogin());
        employeeDAO.update(employee);
    }

    @Override
    public void deleteEmployee(EmployeeDTO employeeDTO) throws SQLException {
        if (employeeDTO.getId() != null) {
            employeeDAO.delete(employeeDAO.get(employeeDTO.getId()));
        } else
            throw new NullPointerException();

    }

    @Override
    public boolean register(EmployeeDTO employeeDTO, String password, Role role) {
        String login = employeeDTO.getLogin();
        try {
            if (employeeDAO.getEmployeeByLogin(login) != null) {
                logger.warn("already exist with login {}", login);
                throw new UserAlreadyExists("User already registered with this login");
            } else {
                createEmployee(employeeDTO,password, role);
            }
        } catch (NoResultException | SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean login(String login, String passWord) throws NoSuchFieldException, SQLException, UserNotFound {
        try {
            Employee employee = employeeDAO.getEmployeeByLogin(login);
            if (employee.getLogin().equals(login)) {
                if (PasswordHasher.checkPass(passWord, employee.getPassWord())) {
                    logger.info("successfully loged in{}", employee);
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
