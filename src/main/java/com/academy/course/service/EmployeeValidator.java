package com.academy.course.service;


import com.academy.course.dto.EmployeeDTO;
import com.academy.course.utils.Role;

public interface EmployeeValidator{
    void employeeCreationValidator(EmployeeDTO employeeDTO, Role role) ;
    void passwordInputValidator(String password);
    void loginInputValidator(String login);
    void addNewOrderValidator(EmployeeDTO employeeDTO);
    void registerValidation(String login, String passWord);
    void validateExistingLogin(String login);
    void findByLoginValidation(String login);
    void tryToLoginValidation(String login,String password) throws NoSuchFieldException;
}
