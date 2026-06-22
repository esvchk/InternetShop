package com.academy.course.service;

import com.academy.course.dao.employeeDao.EmployeeDAO;
import com.academy.course.dao.employeeDao.EmployeeDAOImpl;
import com.academy.course.dto.EmployeeDTO;
import com.academy.course.dto.OrderDTO;
import com.academy.course.dto.ProductDTO;
import com.academy.course.mapper.*;
import com.academy.course.mapper.factory.MapperFactory;
import com.academy.course.model.Employee;
import com.academy.course.model.Order;

import com.academy.course.paginator.PaginatedResult;
import com.academy.course.service.validator.*;
import com.academy.course.utils.Role;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.ValidationException;
import java.sql.SQLException;
import java.util.*;

public class EmployeeServiceImpl extends PaginatedResult <EmployeeDTO> implements EmployeeService {

    private static final Logger logger = LogManager.getLogger(EmployeeServiceImpl.class);
    private final EmployeeDAO employeeDAO;
    private final BaseEmployeeValidator baseEmployeeValidator;
    private final EmployeeMapper employeeMapper;
    private final IdValidatorFactory factory;
    private final OrderService orderService;
    private final BusinessEmployeeValidator businessEmployeeValidator;
    private final BasePaginationValidation basePaginationValidation;

    public EmployeeServiceImpl(EmployeeDAO employeeDAO, BaseEmployeeValidator baseEmployeeValidator, EmployeeMapper employeeMapper, IdValidatorFactory factory, OrderService orderService, BusinessEmployeeValidator businessEmployeeValidator, BasePaginationValidation basePaginationValidation) {
        super(EmployeeDTO.class);
        this.employeeDAO = employeeDAO;
        this.baseEmployeeValidator = baseEmployeeValidator;
        this.employeeMapper = employeeMapper;
        this.factory = factory;
        this.orderService = orderService;
        this.businessEmployeeValidator = businessEmployeeValidator;
        this.basePaginationValidation = basePaginationValidation;
    }


    @Override
    public void deleteOrderOfEmployee(EmployeeDTO employeeDTO, OrderDTO orderDTO) throws SQLException {
        factory.getEmployeeValidator().validateId(employeeDTO.getId());
        factory.getOrderValidator().validateId(orderDTO.getId());
        Employee employee = employeeDAO.get(employeeDTO.getId());
        employee.getOrders().removeIf(order1 -> order1.getId().equals(orderDTO.getId()));
        employeeDAO.update(employee);
        logger.info("Order with id {} successfully deleted from Employee with login{}"
                ,orderDTO.getId(),employeeDTO.getLogin());
    }

    @Override
    public Set<EmployeeDTO> getAllEmployees() throws ValidationException {
        businessEmployeeValidator.getAllEmployeesValidation();
        return employeeMapper.mapToSetDTOS(employeeDAO.getAllEmployees());
    }

    @Override
    public PaginatedResult<EmployeeDTO> getAllEmployees(int offset, int size) {
        Long totalSize = employeeDAO.countProducts();
        Set<EmployeeDTO> employees =
                employeeMapper.mapToSetDTOS(employeeDAO.getAllEmployees((offset - 1) * size, size));
        basePaginationValidation.validatePagination(offset,size,totalSize,employees.size());
        logger.info("Successful getting pages of products");
        return paginate(offset,size,totalSize,employees);
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
    public boolean registerEmployee(String login, String pass, Role role) throws SQLException {

        businessEmployeeValidator.registrationValidation(login, pass, role);

        Employee employee = Employee.builder()
                .login(login)
                .role(role)
                .passWord(PasswordHasher.hashPass(pass))
                .build();

        Order order = Order.builder()
                .employee(employee)
                .isBought(false)
                .build();

        employee.addOrder(order);

        employeeDAO.save(employee);

        logger.info("Employee {} successfully registered",login);
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
        businessEmployeeValidator.authorizationValidation(login,passWord);
        logger.info("Login {} successfully authorized",login);
        return true;
    }

    @Override
    public Double getTotalAmountOfOrders() throws SQLException {
        Set<OrderDTO> orderDTOS = orderService.getAllOrdersWithItems();
        double totalAmount = 0;
        for (OrderDTO orderDTO : orderDTOS){
            if (orderDTO.getIsBought()) {
                totalAmount += orderService.countAmountOfAllItems(orderDTO);
            }
        }
        return totalAmount;
    }

}
