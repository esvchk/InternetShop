package com.academy.course.mapper;

import java.util.List;

public interface Mapper<E, D> {

    D mapToDTO(E entity);

    E mapToEntity(D dto);

    List<E> mapToListEntities(List<D> dtoList);

    List<D> mapToListDTOS(List<E> entityList);
}
