package com.academy.course.mapper;

import java.util.Set;

public interface Mapper<E, D> {

    D mapToDTO(E entity);

    E mapToEntity(D dto);

    Set<E> mapToSetEntities(Set<D> dtoSet);

    Set<D> mapToSetDTOS(Set<E> entitySet);



}
