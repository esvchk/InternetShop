package com.academy.course.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order extends DataEntity implements Serializable {

    @OneToMany(mappedBy = "order",cascade = CascadeType.REMOVE,orphanRemoval = true)
    private List<Item> items = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column
    private Boolean isBought;


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Order order = (Order) o;
        return Objects.equals(getId(),order.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Order{"+ "OrderId=" + this.getId() + "," +
                " customer=" + customer +
                '}';
    }
}
