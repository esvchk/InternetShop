package com.academy.course.model;

import com.academy.course.utils.Discount;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
@ToString
public class Item extends DataEntity {
    @Column
    private Integer productQuantity;

    @Column
    @Enumerated(EnumType.ORDINAL)
    private Discount discount;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    private Order order;


    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        Item item = (Item) object;
        return Objects.equals(getId(),item.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
