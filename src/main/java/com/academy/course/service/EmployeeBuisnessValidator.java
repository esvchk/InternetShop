package com.academy.course.service;

import com.academy.course.dao.employeeDao.EmployeeDAO;
import com.academy.course.dao.employeeDao.EmployeeDAOImpl;
import com.academy.course.dto.EmployeeDTO;
import com.academy.course.exception.EmptyFieldException;
import com.academy.course.exception.LoginValidationException;
import com.academy.course.exception.PasswordValidationException;
import com.academy.course.utils.Role;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class EmployeeBuisnessValidator implements EmployeeValidator {

    private static final Logger logger = LogManager.getLogger(EmployeeBuisnessValidator.class);
    private final EmployeeDAO employeeDAO = new EmployeeDAOImpl();
    private final IdValidatorFactory validatorFactory;

    public EmployeeBuisnessValidator() {
        this.validatorFactory = new IdValidatorFactory();
        this.validatorFactory.setEmployeeDAO(employeeDAO);
    }

    @Override
    public void employeeCreationValidator(EmployeeDTO employeeDTO, Role role) {
            if (employeeDTO.getLogin() == null) {
                logger.warn("empty field {}", employeeDTO.getLogin());
                throw new EmptyFieldException("login");
            }
            if (role == null) {
                logger.warn("employee must have a role");
                throw new EmptyFieldException("role");
            }

    }

    @Override
    public void passwordValidator(String password) {
        if (password == null) {
            logger.warn("employee must have a password");
            throw new EmptyFieldException("password");
        }
        List<String> errors = new ArrayList<>();
        if (password.length() < 8 || password.length() > 15) {
            errors.add("Password must be above 8 and less 15 symbols");
        }
        String upperCase = "(.*[A-Z].*)";
        if (!password.matches(upperCase)) {
            errors.add("Password must contain at least 1 uppercase letter");

        }
        String lowerCase = "(.*[a-z].*)";
        if (!password.matches(lowerCase)) {
            errors.add("Password must have at least 1 lowercase letter");

        }
        String figure = "(.*[0-9].*)";
        if (!password.matches(figure)) {
            errors.add("Password must have at least 1 figure");

        }
        if (!errors.isEmpty()) {
            String error = String.join(",",errors);
            logger.warn("Password validation failed {}",error);
            throw new PasswordValidationException(error);
        }

    }

    @Override
    public void loginValidator(String login) {
        if (login == null) {
            throw new EmptyFieldException(login);
        }
        List<String> errors = new ArrayList<>();
        if (login.length() < 3 || login.length() > 10) {
            errors.add("Login length must be above 3 and below 10");
        }
        String banedFigueres = "(.*[0-9].*)";
        if (login.matches(banedFigueres)) {
            errors.add("Login mustn't contain figures");
        }
        if (!errors.isEmpty()) {
            String error = String.join(",",errors);
            logger.warn("Login validation failed{}",error);
            throw new LoginValidationException(error);
        }

    }







}
