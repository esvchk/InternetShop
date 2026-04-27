package com.academy.course.dto;

import com.academy.course.model.DataEntity;
import com.academy.course.model.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO extends DataEntity {



    private Integer id;
    private Set<Product> products;

    @Override
    public String toString() {
        return "OrderDTO{" +
                "id=" + id +

                ", products=" + products +
                '}';
    }
}
