package com.academy.course.dto;

import com.academy.course.model.Customer;
import com.academy.course.model.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {

    private Integer id;

    private String login;

    private String email;

    private Set<OrderDTO> orderDTOs;

    @Override
    public String toString() {
        return "CustomerDTO{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", orderDTOs=" + orderDTOs +
                '}';
    }
}
