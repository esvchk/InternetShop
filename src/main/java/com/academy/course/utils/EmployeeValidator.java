package com.academy.course.utils;


import com.academy.course.dto.EmployeeDTO;
import com.academy.course.exception.EmptyData;

public class EmployeeValidator {

    private Boolean employeeIsValid(EmployeeDTO employeeDTO){

        if (employeeDTO.getId() == null) {
            throw new EmptyData("emptyid");
        }
        if (employeeDTO.getLogin() == null) {
            throw new EmptyData("emptyLogin");
        }

        if (employeeDTO.getRole() == null) {
            throw new EmptyData("empty role");
        }
        return true;
    }

}
