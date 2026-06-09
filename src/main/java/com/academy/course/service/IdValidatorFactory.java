package com.academy.course.service;

import com.academy.course.dao.categoryDao.CategoryDAO;
import com.academy.course.dao.employeeDao.EmployeeDAO;
import com.academy.course.dao.employeeDao.EmployeeDAOImpl;
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

    public IdValidator getEmployeeValidator() {
        return new IdValidationService(entityId -> employeeDAO.get(entityId) != null, "Employee");
    }

    public IdValidator getProductValidator() {
        return new IdValidationService(entityId -> productDAO.get(entityId) != null, "Product");
    }

    public IdValidator getOrderValidator() {
        return new IdValidationService(entityId -> orderDAO.get(entityId) != null, "Order");
    }

    public IdValidator getCategoryValidator() {
        return new IdValidationService(entityId -> categoryDAO.get(entityId) != null, "Category");
    }

    public IdValidator getItemValidator() {
        return new IdValidationService(entityId -> itemDAO.get(entityId) != null, "Item");
    }
}
