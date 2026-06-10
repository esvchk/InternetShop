package com.academy.course.service;

import com.academy.course.dto.EmployeeDTO;
import com.academy.course.utils.Role;

public interface BaseEmployeeValidator {
    void passwordInputValidator(String password);
    void loginInputValidator(String login);
    void addNewOrderValidator(EmployeeDTO employeeDTO);
    void updatingValidation(EmployeeDTO newValue);

}
