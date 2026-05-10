package com.academy.course.dto;

import com.academy.course.model.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

    private Integer id;

    private CustomerDTO customerDTO;

    private List<ProductDTO> productDTO;

    private Boolean isBought;

    private LocalDateTime dateTimeOfCreation;

    private LocalDateTime dateTimeOfPurchasing;


    @Override
    public String toString() {
        return "OrderDTO{" +
                "id=" + id +
                ", customerDTO=" + customerDTO +
                ", productDTO=" + productDTO +
                ", isBought=" + isBought +
                ", dateTimeOfCreation=" + dateTimeOfCreation +
                ", dateTimeOfPurchasing=" + dateTimeOfPurchasing +
                '}';
    }
}
