package com.academy.course.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
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
