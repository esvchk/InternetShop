package com.academy.course.utils;

import lombok.Getter;

@Getter
public enum Discount {
    PERMANENT_CUSTOMER(0.15),
    CASHIER(0.3),
    ADMINISTRATOR(0.4),
    MANAGER(0.6);

    private final Double percentOfDiscount;

    Discount(Double percentOfDiscount) {
        this.percentOfDiscount = percentOfDiscount;
    }

    public Double countCostWithDiscount(Double price){
        Double discountAmount = price * percentOfDiscount ;
        return price - discountAmount;
    }


}
