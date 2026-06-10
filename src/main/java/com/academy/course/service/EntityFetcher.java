package com.academy.course.service;

import java.sql.SQLException;

public interface EntityFetcher {
    boolean existsById(Integer entityId) throws SQLException;



}
