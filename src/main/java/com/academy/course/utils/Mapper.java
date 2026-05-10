package com.academy.course.utils;

public interface Mapper<E,D> {

    D mapToDTO(E entity);

    E mapToEntity(D dto);
}
