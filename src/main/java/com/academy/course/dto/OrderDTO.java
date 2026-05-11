package com.academy.course.dto;

import com.academy.course.model.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

    private Integer id;

    private List<ItemDTO> itemsDTO;

    private CustomerDTO customerDTO;

    private Boolean isBought;

    @Override
    public String toString() {
        return "OrderDTO{" +
                "id=" + id +
                ", itemDTO=" + itemsDTO +
                ", customerDTO=" + customerDTO +
                ", isBought=" + isBought +
                '}';
    }
}
