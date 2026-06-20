package com.academy.course.paginator;

import lombok.Data;

import java.util.Set;
@Data
public class PaginatedResult<T> implements Paginator<T> {
    private Integer currentPage;
    private Integer lastPage;
    private Integer pageSize;
    private Long totalSize;
    private Set<T> entities;

    private final Class<T> clazz;

    public PaginatedResult(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public PaginatedResult<T> paginate(Integer currenPage, Integer pageSize, Long listSize, Set<T> setToPaginate) {
        int lastPageNumber;
        if (listSize % pageSize == 0) {
            lastPageNumber = (int) (listSize / pageSize);
        } else {
            lastPageNumber = (int) (listSize / pageSize) + 1;
        }
        PaginatedResult<T> paginator = new PaginatedResult<>(clazz);
        paginator.setCurrentPage(currenPage);
        paginator.setEntities(setToPaginate);
        paginator.setLastPage(lastPageNumber);
        paginator.setPageSize(pageSize);
        paginator.setTotalSize(listSize);
        return paginator;
    }
}
