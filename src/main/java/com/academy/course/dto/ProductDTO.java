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

    private Integer id;

    private String name;

    private Double price;

    private String info;

    private String manufacturer;

    private LocalDate bestBefore;


    @Override
    public String toString() {
        return "ProductDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", info='" + info + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", bestBefore=" + bestBefore +
                '}';
    }


}
