package com.academy.course.mapper;

import com.academy.course.dto.ItemDTO;
import com.academy.course.model.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemMapper implements Mapper<Item, ItemDTO> {

    private final ProductMapper productMapper = new ProductMapper();

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
                .order(null)
                .build();
    }

    @Override
    public List<Item> mapToListEntities(List<ItemDTO> dtoList) {
        List<Item> items = new ArrayList<>();
        for (ItemDTO itemDTO : dtoList){
            Item item = Item.builder()
                    .productQuantity(itemDTO.getQuantity())
                    .product(productMapper.mapToEntity(itemDTO.getProductDTO()))
                    .order(null)
                    .build();
            items.add(item);
        }
        return items;
    }

    @Override
    public List<ItemDTO> mapToListDTOS(List<Item> entityList) {
        List<ItemDTO> itemDTOS = new ArrayList<>();
        for (Item item : entityList){
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
