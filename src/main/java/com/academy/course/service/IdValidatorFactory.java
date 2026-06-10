package com.academy.course.service;

import com.academy.course.dao.categoryDao.CategoryDAO;
import com.academy.course.dao.employeeDao.EmployeeDAO;
import com.academy.course.dao.itemDao.ItemDAO;
import com.academy.course.dao.orderDao.OrderDAO;
import com.academy.course.dao.productDao.ProductDAO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class IdValidatorFactory {
    private EmployeeDAO employeeDAO;
    private ProductDAO productDAO;
    private OrderDAO orderDAO;
    private CategoryDAO categoryDAO;
    private ItemDAO itemDAO ;

    public Validator getEmployeeValidator() {
        return new ValidationService(entityId -> employeeDAO.get(entityId) != null, "Employee");
    }

    public Validator getProductValidator() {
        return new ValidationService(entityId -> productDAO.get(entityId) != null, "Product");
    }

    public Validator getOrderValidator() {
        return new ValidationService(entityId -> orderDAO.get(entityId) != null, "Order");
    }

    public Validator getCategoryValidator() {
        return new ValidationService(entityId -> categoryDAO.get(entityId) != null, "Category");
    }

    public Validator getItemValidator() {
        return new ValidationService(entityId -> itemDAO.get(entityId) != null, "Item");
    }

}
