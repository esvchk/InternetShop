package com.academy.course.service;

import java.sql.SQLException;

public interface EntityFetcher {
    boolean exists(Integer entityId) throws SQLException;

}
