package com.academy.course.model;

import lombok.*;

import javax.persistence.*;
import java.util.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
@ToString
public class Order extends DataEntity {

    @ToString.Exclude
    @OneToMany(mappedBy = "order",cascade = CascadeType.REMOVE,orphanRemoval = true,fetch = FetchType.LAZY)
    @Builder.Default
    private Set<Item> items = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER)
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

    public void addItem(Item item){
        if (this.getItems()==null){
            this.setItems(new HashSet<>());
        }
        this.items.add(item);
        item.setOrder(this);
    }

    public void removeItem(Item item){
        if (this.getItems() == null) {
            this.setItems(new HashSet<>());
        }
        this.items.remove(item);
        item.setOrder(null);
    }



}
