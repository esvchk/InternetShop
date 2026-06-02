package com.academy.course.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;


@EqualsAndHashCode
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table
public class Product extends DataEntity implements Serializable {

    @Column
    private String name;

    @Column
    private Double price;

    @Column
    private String info;

    @Column
    private Boolean isAvailable;


}
