package com.academy.course.service;

import com.academy.course.dto.OrderDTO;
import com.academy.course.dto.ProductDTO;

import java.util.AbstractMap;
import java.util.List;
import java.util.Set;

public interface TablesService {

    Set<Object> getPairedList(String login);
}
