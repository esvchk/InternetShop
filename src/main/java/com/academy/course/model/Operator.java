package com.academy.course.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Operator extends DataEntity implements Serializable {

    @Column
    private String login;
    @Column
    private String passWord;
    @Column
    private String accessLevel;

    @OneToMany(mappedBy = "operator")
    private Set<Order> orders = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Operator operator = (Operator) o;
        return Objects.equals(getId(), operator.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
