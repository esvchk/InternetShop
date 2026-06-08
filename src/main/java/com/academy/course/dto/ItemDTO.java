package com.academy.course.dto;

import com.academy.course.utils.Discount;
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

    private Discount discount;

    @Override
    public String toString() {
        return "ItemDTO{" +
                "id=" + id +
                ", productDTO=" + productDTO +
                ", quantity=" + quantity +
                '}';
    }
}
