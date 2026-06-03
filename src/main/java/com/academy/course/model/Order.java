package com.academy.course.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;
@EqualsAndHashCode
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name = "orders")
public class Order extends DataEntity implements Serializable {

    @ToString.Exclude
    @OneToMany(mappedBy = "order",cascade = CascadeType.REMOVE,orphanRemoval = true,fetch = FetchType.LAZY)
    private Set<Item> items = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column
    private Boolean isBought;

}
