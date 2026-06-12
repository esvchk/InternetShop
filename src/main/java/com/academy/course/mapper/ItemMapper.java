package com.academy.course.mapper;

import com.academy.course.dto.ItemDTO;
import com.academy.course.exception.EmptyEntityException;
import com.academy.course.model.Item;
import com.academy.course.service.validator.EmptyFieldValidator;

import java.util.HashSet;
import java.util.Set;

public class ItemMapper implements Mapper<Item, ItemDTO>, EmptyFieldValidator<Object> {

    private final ProductMapper productMapper;

    public ItemMapper(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }


    @Override
    public ItemDTO mapToDTO(Item entity) {
        validateField(entity);
        return ItemDTO.builder()
                .id(entity.getId())
                .discount(entity.getDiscount())
                .productDTO(productMapper.mapToDTO(entity.getProduct()))
                .quantity(entity.getProductQuantity())
                .build();
    }

    @Override
    public Item mapToEntity(ItemDTO dto) {
        validateField(dto);
        return Item.builder()
                .discount(dto.getDiscount())
                .productQuantity(dto.getQuantity())
                .product(productMapper.mapToEntity(dto.getProductDTO()))
                .build();
    }

    @Override
    public Set<Item> mapToSetEntities(Set<ItemDTO> dtoSet) {
        Set<Item> items = new HashSet<>();
        for (ItemDTO itemDTO : dtoSet){
            Item item = Item.builder()
                    .discount(itemDTO.getDiscount())
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
                    .discount(item.getDiscount())
                    .productDTO(productMapper.mapToDTO(item.getProduct()))
                    .quantity(item.getProductQuantity())
                    .build();
            itemDTOS.add(itemDTO);
        }
        return itemDTOS;
    }


    @Override
    public void validateField(Object object) {
        if (object == null) {
            throw new EmptyEntityException(object);
        }
    }
}
