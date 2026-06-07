package com.academy.course.mapper;

public enum MapperFactory {

    INSTANCE;

    private static final ProductMapper PRODUCT_MAPPER = new ProductMapper();
    private static final CategoryMapper CATEGORY_MAPPER = new CategoryMapper(PRODUCT_MAPPER);
    private static final ItemMapper ITEM_MAPPER = new ItemMapper(PRODUCT_MAPPER);
    private static final OrderMapper ORDER_MAPPER = new OrderMapper(ITEM_MAPPER);
    private static final CustomerMapper CUSTOMER_MAPPER = new CustomerMapper(ORDER_MAPPER);

    public static CustomerMapper getCustomerMapper() {
        return CUSTOMER_MAPPER;
    }

    public static CategoryMapper getCategoryMapper() {
        return CATEGORY_MAPPER;
    }

    public static OrderMapper getOrderMapper() {
        return ORDER_MAPPER;
    }

    public static ProductMapper getProductMapper() {
        return PRODUCT_MAPPER;
    }

    public static ItemMapper getItemMapperMapper() {
        return ITEM_MAPPER;
    }
}

