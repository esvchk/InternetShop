package com.academy.course.dto;

import lombok.*;

import java.util.Set;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {

    private Integer id;

    private String name;

    private Set<ProductDTO> productsDTO;

}
