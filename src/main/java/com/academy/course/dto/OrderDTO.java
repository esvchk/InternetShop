package com.academy.course.dto;

import com.academy.course.model.Customer;
import com.academy.course.model.DataEntity;
import com.academy.course.model.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Set;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO extends DataEntity {

    private Integer id;

    private Customer customer;

    private boolean isBought;

    private LocalDateTime dateTimeOfCreation;

    private LocalDateTime dateTimeOfPurchasing;

    @Override
    public String toString() {
        return "OrderDTO{" +
                "id=" + id +
                ", customer=" + customer +
                ", isBought=" + isBought +
                ", dateTimeOfCreation=" + dateTimeOfCreation +
                ", dateTimeOfPurchasing=" + dateTimeOfPurchasing +
                '}';
    }
}
