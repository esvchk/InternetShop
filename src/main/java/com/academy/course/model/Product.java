package com.academy.course.model;

import lombok.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Product extends DataEntity implements Serializable {

    @Column
    private String name;

    @Column
    private Double price;

    @Column
    private String info;

    @Column
    private String manufacturer;

    @Column
    private LocalDate bestBefore;

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
