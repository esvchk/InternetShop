package com.academy.course.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {

    private Integer id;

    private String login;

    private String email;

    private String address;

    private LocalDate birthday;

    private LocalDateTime dateTimeOfRegistration;

    @Override
    public String toString() {
        return "CustomerDTO{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", birthday=" + birthday +
                ", dateTimeOfRegistration=" + dateTimeOfRegistration +
                '}';
    }
}
