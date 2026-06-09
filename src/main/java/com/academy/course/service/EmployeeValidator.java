package com.academy.course.service;


import com.academy.course.dto.EmployeeDTO;
import com.academy.course.utils.Role;

import java.sql.SQLException;

public interface EmployeeValidator {
    void employeeCreationValidator(EmployeeDTO employeeDTO, Role role) ;
    void passwordValidator(String password);

}
