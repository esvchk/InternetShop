package com.academy.course.paginator;

import java.util.Set;

public interface Paginator<T> {
    PaginatedResult<T> paginate(Integer currenPage, Integer pageSize, Long listSize, Set<T> setToPaginate);

}
