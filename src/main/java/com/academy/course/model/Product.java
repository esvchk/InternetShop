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
@Table
@ToString
public class Product extends DataEntity {

    @Column
    private String name;

    @Column
    private Double price;

    @Column
    private String info;

    @Column
    private Boolean isAvailable;

    @Column
    private Integer productLimit;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "product",cascade = CascadeType.REMOVE,orphanRemoval = true)
    private Set<Item> items = new HashSet<>();


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Product product = (Product) o;
        return Objects.equals(getId(),product.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }


}
