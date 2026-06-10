package com.academy.course.service;


import com.academy.course.dto.EmployeeDTO;
import com.academy.course.utils.Role;

public interface BusinessEmployeeValidator {
    void employeeRegistrationValidator(EmployeeDTO employeeDTO,String password, Role role) ;
    void validateExistingLogin(String login);
    void findByLoginValidation(String login);
    void tryToLoginValidation(String login,String password) throws NoSuchFieldException;

}
