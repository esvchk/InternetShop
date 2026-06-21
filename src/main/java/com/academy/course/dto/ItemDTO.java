package com.academy.course.dto;

import com.academy.course.utils.Discount;
import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDTO {

    private Integer id;

    private ProductDTO productDTO;

    private Integer quantity;

    private Discount discount;

    
}
