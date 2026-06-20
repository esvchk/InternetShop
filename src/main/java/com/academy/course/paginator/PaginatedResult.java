package com.academy.course.paginator;

import lombok.Data;

import java.util.Set;
@Data
public class PaginatedResult<T> implements Paginator<T> {
    private Integer currentPage;
    private Integer lastPage;
    private Integer pageSize;
    private Long totalRecords;
    private Set<T> entities;

    private final Class<T> clazz;

    public PaginatedResult(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public PaginatedResult<T> paginate(Integer offSet, Integer size, Long totalSize, Set<T> setToPaginate) {
        int lastPageNumber;
        if (totalSize % size == 0) {
            lastPageNumber = (int) (totalSize / size);
        } else {
            lastPageNumber = (int) (totalSize / size) + 1;
        }
        PaginatedResult<T> paginator = new PaginatedResult<>(clazz);
        paginator.setCurrentPage(offSet);
        paginator.setEntities(setToPaginate);
        paginator.setLastPage(lastPageNumber);
        paginator.setPageSize(size);
        paginator.setTotalRecords(totalSize);
        return paginator;
    }
}
