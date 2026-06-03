package com.academy.course.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
@ToString
public class Category extends DataEntity{

    private String name;

    @ToString.Exclude
    @OneToMany(mappedBy = "category",fetch = FetchType.LAZY,orphanRemoval = true)
    private Set<Product> products = new HashSet<>();


    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        Category category = (Category) object;
        return Objects.equals(getId(),category.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
