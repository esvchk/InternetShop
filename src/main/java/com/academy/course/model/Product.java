package com.academy.course.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
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
