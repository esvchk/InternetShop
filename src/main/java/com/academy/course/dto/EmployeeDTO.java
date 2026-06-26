package com.academy.course.dto;

import com.academy.course.utils.Role;
import lombok.*;

import java.util.Set;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {

    private Integer id;

    private String login;

    private Role role;

    private Set<OrderDTO> orderDTOs;


}
