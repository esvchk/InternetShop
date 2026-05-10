package com.academy.course.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDTO {

    private Integer id;

    private ProductDTO productDTO;

    private Integer quantity;

    @Override
    public String toString() {
        return "OrderItemDTO{" +
                "id=" + id +
                ", productDTO=" + productDTO +
                ", quantity=" + quantity +
                '}';
    }
}
