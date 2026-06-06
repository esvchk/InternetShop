package com.academy.course.mapper;

import com.academy.course.dto.ItemDTO;
import com.academy.course.model.Item;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ItemMapper implements Mapper<Item, ItemDTO> {

    private final ProductMapper productMapper;

    public ItemMapper(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }


    @Override
    public ItemDTO mapToDTO(Item entity) {
        return ItemDTO.builder()
                .id(entity.getId())
                .productDTO(productMapper.mapToDTO(entity.getProduct()))
                .quantity(entity.getProductQuantity())
                .build();
    }

    @Override
    public Item mapToEntity(ItemDTO dto) {
        return Item.builder()
                .productQuantity(dto.getQuantity())
                .product(productMapper.mapToEntity(dto.getProductDTO()))
                .build();
    }

    @Override
    public Set<Item> mapToSetEntities(Set<ItemDTO> dtoSet) {
        Set<Item> items = new HashSet<>();
        for (ItemDTO itemDTO : dtoSet){
            Item item = Item.builder()
                    .productQuantity(itemDTO.getQuantity())
                    .product(productMapper.mapToEntity(itemDTO.getProductDTO()))
                    .build();
            items.add(item);
        }
        return items;
    }

    @Override
    public Set<ItemDTO> mapToSetDTOS(Set<Item> entitySet) {
        Set<ItemDTO> itemDTOS = new HashSet<>();
        for (Item item : entitySet){
            ItemDTO itemDTO = ItemDTO.builder()
                    .id(item.getId())
                    .productDTO(productMapper.mapToDTO(item.getProduct()))
                    .quantity(item.getProductQuantity())
                    .build();
            itemDTOS.add(itemDTO);
        }
        return itemDTOS;
    }
    

}
