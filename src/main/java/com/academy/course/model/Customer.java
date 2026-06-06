package com.academy.course.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
@ToString
public class Customer extends DataEntity {

    @Column
    private String login;

    @Column
    private String passWord;

    @Column
    private String email;

    @ToString.Exclude
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private Set<Order> orders = new HashSet<>();


    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        Customer customer = (Customer) object;
        return Objects.equals(customer.getId(), getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

}

