package com.academy.course.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class Customer extends DataEntity implements Serializable {

    @Column
    private String login;

    @Column
    private String passWord;

    @Column
    private String email;

    @Column
    private String payment;


    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "customer_orders",
            joinColumns = {@JoinColumn(name = "customer_id")},
            inverseJoinColumns = {@JoinColumn(name = "order_id")})
    private Set<Order> orders = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Customer customer = (Customer) o;
        return Objects.equals(getId(),customer.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}

