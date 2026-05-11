package com.academy.course.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderShortDTO {

    private Integer id;

    private Boolean isBought;

    @Override
    public String toString() {
        return "OrderShortDTO{" +
                "id=" + id +
                ", isBought=" + isBought +
                '}';
    }
}
