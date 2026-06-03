package com.academy.course.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@EqualsAndHashCode(callSuper = false)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
@ToString
public class Category extends DataEntity implements Serializable {

    private String name;

    @ToString.Exclude
    @OneToMany(mappedBy = "category",fetch = FetchType.LAZY,orphanRemoval = true)
    private Set<Product> products = new HashSet<>();


}
