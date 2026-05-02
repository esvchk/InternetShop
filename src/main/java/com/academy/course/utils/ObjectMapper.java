package com.academy.course.utils;

public interface ObjectMapper<ENTITY, DTO> {

    DTO mapToDTO(ENTITY entity);

    ENTITY mapToEntity(DTO dto);
}
