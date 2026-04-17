package com.academy.course.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product extends DataEntity {

    @Column
    private String name;

    @Column
    private Double price;

    @Column
    private String info;

    @CreationTimestamp
    private LocalDateTime createDateTime;

    @UpdateTimestamp
    @Column(insertable = false)
    private LocalDateTime updateDateTime;
}
