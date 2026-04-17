package com.academy.course.dto;

import com.academy.course.model.DataEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private String name;

    private Double price;

    private String info;

    private String manufacturer;

    private LocalDate bestBefore;


}
