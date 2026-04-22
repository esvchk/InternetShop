package com.academy.course.dto;

import com.academy.course.model.DataEntity;
import com.academy.course.model.Operator;
import com.academy.course.model.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO extends DataEntity {

    public OrderDTO(Operator operator, Set<Product> products) {
        this.operator = operator;
        this.products = products;
    }

    private Integer id;
    private Operator operator;
    private Set<Product> products;

    @Override
    public String toString() {
        return "OrderDTO{" +
                "id=" + id +
                ", operator=" + operator +
                ", products=" + products +
                '}';
    }
}
