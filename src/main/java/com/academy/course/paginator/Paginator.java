package com.academy.course.paginator;

import java.util.Set;

public interface Paginator<T> {
    PaginatedResult<T> paginate(Integer offSet, Integer size, Long totalSize, Set<T> setToPaginate);
}
