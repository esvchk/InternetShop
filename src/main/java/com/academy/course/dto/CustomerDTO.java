package com.academy.course.dto;

import javax.persistence.Column;
import java.time.LocalDateTime;

public class CustomerDTO {

    private Integer id;

    private String login;

    private String email;

    private String payment;

    private LocalDateTime dateTimeOfOrder;

    @Override
    public String toString() {
        return "CustomerDTO{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", payment='" + payment + '\'' +
                ", dateTimeOfOrder=" + dateTimeOfOrder +
                '}';
    }
}
