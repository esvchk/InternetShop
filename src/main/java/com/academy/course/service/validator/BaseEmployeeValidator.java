package com.academy.course.service.validator;

import com.academy.course.dto.EmployeeDTO;

public interface BaseEmployeeValidator {
    void passwordInputValidator(String password);
    void loginInputValidator(String login);
    void addNewOrderValidator(EmployeeDTO employeeDTO);
    void updatingValidation(EmployeeDTO newValue);

}
