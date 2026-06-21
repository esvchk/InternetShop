package com.academy.course.dto;

import lombok.*;

import java.util.Set;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

    private Integer id;

    private Set<ItemDTO> itemsDTO;

    private String paymentData;

    private Double totalCost;

    private Boolean isBought;


    @Override
    public String toString() {
        return "OrderDTO{" +
                "id=" + id +
                ", itemsDTO=" + itemsDTO +
                ", paymentData=" + paymentData +
                ", isBought=" + isBought +
                '}';
    }
}
