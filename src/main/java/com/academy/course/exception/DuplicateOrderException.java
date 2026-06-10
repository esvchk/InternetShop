package com.academy.course.exception;

import com.academy.course.dto.EmployeeDTO;
import com.academy.course.model.Employee;
import lombok.Getter;

@Getter
public class DuplicateOrderException extends BusinessException{
    public DuplicateOrderException(String login) {
        super("Employee with login " + login +
                " cannot have more than 1 unbought order. Delete or buy unbought order");
        this.login = login;
    }

    private final String login;

}
