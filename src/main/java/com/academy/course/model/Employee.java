package com.academy.course.model;

import com.academy.course.utils.Role;
import lombok.*;

import javax.persistence.*;
import java.util.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
@ToString
public class Employee extends DataEntity {

    @Column
    private String login;

    @Column
    private String passWord;

    @Column
    private String paymentData;

    @Column
    @Enumerated(EnumType.STRING)
    private Role role;

    @ToString.Exclude
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private Set<Order> orders = new HashSet<>();


    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        Employee employee = (Employee) object;
        return Objects.equals(employee.getId(), getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    public void addOrder(Order order){
        if (this.orders == null) {
            this.orders = new HashSet<>();
        }
        this.orders.add(order);
        order.setEmployee(this);
    }

}

