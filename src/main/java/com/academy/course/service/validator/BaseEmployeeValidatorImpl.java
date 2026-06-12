package com.academy.course.service.validator;

import com.academy.course.dto.EmployeeDTO;
import com.academy.course.dto.OrderDTO;
import com.academy.course.exception.DuplicateOrderException;
import com.academy.course.exception.EmptyFieldException;
import com.academy.course.exception.LoginValidationException;
import com.academy.course.exception.PasswordValidationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BaseEmployeeValidatorImpl implements BaseEmployeeValidator, EmptyFieldValidator<String> {

    private static final Logger logger = LogManager.getLogger(BaseEmployeeValidatorImpl.class);

    @Override
    public void passwordInputValidator(String password) {

        validateField(password);

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
            String error = String.join(",", errors);
            logger.warn("Password validation failed {}", error);
            throw new PasswordValidationException(error);
        }

    }

    @Override
    public void loginInputValidator(String login) {
        validateField(login);
        List<String> errors = new ArrayList<>();
        if (login.length() < 3 || login.length() > 10) {
            errors.add("Login length must be above 3 and below 10");
        }
        String banedFigueres = "(.*[0-9].*)";
        if (login.matches(banedFigueres)) {
            errors.add("Login mustn't contain figures");
        }
        if (!errors.isEmpty()) {
            String error = String.join(",", errors);
            logger.warn("Login validation failed{}", error);
            throw new LoginValidationException(error);
        }
    }

    @Override
    public void addNewOrderValidator(EmployeeDTO employeeDTO) {
        Optional<OrderDTO> orderDTO = employeeDTO.getOrderDTOs().stream()
                .filter(orderDTO1 -> orderDTO1.getIsBought() == false).findFirst();
        if (orderDTO.isPresent()) {
            logger.warn("Adding new order validation failed");
            throw new DuplicateOrderException(employeeDTO.getLogin());
        }
    }

    @Override
    public void updatingValidation(EmployeeDTO newValue) {
        loginInputValidator(newValue.getLogin());
        validateField(newValue.getRole().toString());
    }

    @Override
    public void validateField(String fieldName) {
        if (fieldName == null || fieldName.trim().isEmpty()) {
            logger.warn("Try to input empty value in {}", fieldName);
            throw new EmptyFieldException(fieldName);
        }
    }
}
