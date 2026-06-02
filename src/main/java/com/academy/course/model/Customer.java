package com.academy.course.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;
@EqualsAndHashCode(callSuper = false)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
@ToString
public class Customer extends DataEntity implements Serializable {

    @Column
    private String login;

    @Column
    private String passWord;

    @Column
    private String email;

    @ToString.Exclude
    @OneToMany(mappedBy = "customer",cascade = CascadeType.PERSIST)
    private List<Order> orders = new ArrayList<>();


}

