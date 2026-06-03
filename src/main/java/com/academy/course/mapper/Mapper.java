package com.academy.course.mapper;

import java.util.List;
import java.util.Set;

public interface Mapper<E, D> {

    D mapToDTO(E entity);

    E mapToEntity(D dto);

    Set<E> mapToListEntities(Set<D> dtoSet);

    Set<D> mapToListDTOS(Set<E> entitySet);



}
