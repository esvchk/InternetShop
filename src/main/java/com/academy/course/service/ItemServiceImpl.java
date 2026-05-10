package com.academy.course.service;

import com.academy.course.dto.ItemDTO;
import com.academy.course.model.Item;
import com.academy.course.utils.Mapper;

public class ItemServiceImpl implements Mapper<Item, ItemDTO> {
    @Override
    public ItemDTO mapToDTO(Item entity) {
        return ItemDTO.builder()
                .id(entity.getId())
                .productDTO(null)
                .quantity(entity.getProductQuantity())
                .build();
    }

    @Override
    public Item mapToEntity(ItemDTO dto) {
        return Item.builder()
                .productQuantity(dto.getQuantity())
                .product(null)
                .order(null)
                .build();
    }
}
