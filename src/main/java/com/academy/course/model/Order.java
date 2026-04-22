package com.academy.course.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Order extends DataEntity implements Serializable {
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "product_orders",
    joinColumns = {@JoinColumn(name = "order_id")},
    inverseJoinColumns = {@JoinColumn(name = "product_id")})
    private Set<Product> products = new HashSet<>();

    @ManyToMany(mappedBy = "orders")
    private Set<Customer> customers = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "operator_id")
    private Operator operator;

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

}
