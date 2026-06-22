package com.academy.course.service.validator;

import com.academy.course.dao.employeeDao.EmployeeDAO;
import com.academy.course.dto.EmployeeDTO;
import com.academy.course.exception.*;
import com.academy.course.service.PasswordHasher;
import com.academy.course.utils.Role;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.ValidationException;

public class BusinessEmployeeValidatorImpl implements BusinessEmployeeValidator, EmptyFieldValidator<String> {

    private static final Logger logger = LogManager.getLogger(BusinessEmployeeValidatorImpl.class);
    private final BaseEmployeeValidator baseEmployeeValidator;
    private final EmployeeDAO employeeDAO;

    public BusinessEmployeeValidatorImpl(BaseEmployeeValidator baseEmployeeValidator, EmployeeDAO employeeDAO) {
        this.baseEmployeeValidator = baseEmployeeValidator;
        this.employeeDAO = employeeDAO;
    }

    @Override
    public void registrationValidation(String login, String password, Role role) {
        baseEmployeeValidator.loginInputValidator(login);
        validateExistingLogin(login);
        baseEmployeeValidator.passwordInputValidator(password);
        validateField(role.toString());
    }


    @Override
    public void validateExistingLogin(String login) {
        baseEmployeeValidator.loginInputValidator(login);
        if (employeeDAO.getEmployeeByLogin(login) != null) {
            logger.warn("Employee already has been registered with login {}", login);
            throw new EmployeeAlreadyExists(login);
        }
    }

    @Override
    public void findByLoginValidation(String login) {
       baseEmployeeValidator.loginInputValidator(login);
        if (employeeDAO.getEmployeeByLogin(login) == null) {
            logger.warn("Search employee by login{} failed", login);
            throw new EntityNotFoundByNameException(login);
        }
    }

    @Override
    public void authorizationValidation(String login, String password) throws NoSuchFieldException {
        validateField(login);
        if (employeeDAO.getEmployeeByLogin(login) == null ) {
            logger.warn("Employee is not exists{}", login);
            throw new WrongLoginException("Wrong Login");
        }
        validateField(password);
        if (!PasswordHasher.checkPass(password, employeeDAO.getEmployeeByLogin(login).getPassWord())) {
            logger.warn("Wrong PassWord by login{}", login);
            throw new WrongPassWordException("Wrong password");
        }
    }

    @Override
    public void getAllEmployeesValidation(){
        if (employeeDAO.getAllEmployees() == null || employeeDAO.getAllEmployees().isEmpty()) {
            logger.warn("Receiving list Employees failed");
            throw new EmptyListException("Empty list of Employees");
        }
    }


    @Override
    public void validateField(String fieldName) {
        if (fieldName == null || fieldName.trim().isEmpty()) {
            logger.warn("Try to input empty value in {}", fieldName);
            throw new EmptyFieldException(fieldName);
        }
    }
}
