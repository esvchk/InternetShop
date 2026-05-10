package com.academy.course.dto;

import com.academy.course.model.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.criteria.CriteriaBuilder;
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDTO {

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
