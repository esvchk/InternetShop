package com.academy.course.dto;

import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private Integer id;

    private String name;

    private Double price;
    
    private String info;

    private Integer productLimit;

    private Boolean isAvailable;

    private CategoryDTO categoryDTO;



}
