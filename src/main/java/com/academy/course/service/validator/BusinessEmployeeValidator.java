package com.academy.course.service.validator;


import com.academy.course.dto.EmployeeDTO;
import com.academy.course.utils.Role;

import javax.xml.bind.ValidationException;

public interface BusinessEmployeeValidator {
    void registrationValidation(String login,String password, Role role) ;
    void validateExistingLogin(String login);
    void findByLoginValidation(String login);
    void authorizationValidation(String login, String password) throws NoSuchFieldException;
    void getAllEmployeesValidation() throws ValidationException;


}
