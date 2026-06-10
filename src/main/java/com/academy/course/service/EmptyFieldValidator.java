package com.academy.course.service;

import java.lang.reflect.Field;

public interface EmptyFieldValidator<T> {
    void validateField(T t);
}
