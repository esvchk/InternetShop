package com.academy.course;

import com.academy.course.dao.categoryDao.CategoryDAO;
import com.academy.course.dao.categoryDao.CategoryDAOImpl;
import com.academy.course.dao.employeeDao.EmployeeDAO;
import com.academy.course.dao.employeeDao.EmployeeDAOImpl;
import com.academy.course.dao.itemDao.ItemDAO;
import com.academy.course.dao.itemDao.ItemDAOImpl;
import com.academy.course.dao.orderDao.OrderDAO;
import com.academy.course.dao.orderDao.OrderDAOImpl;


import com.academy.course.dao.productDao.ProductDAO;
import com.academy.course.dao.productDao.ProductDAOImpl;
import com.academy.course.mapper.*;
import com.academy.course.model.*;
import com.academy.course.service.*;
import com.academy.course.service.validator.*;
import com.academy.course.utils.Role;

import javax.xml.bind.ValidationException;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public class App {
    public static void main(String[] args) throws SQLException, NoSuchFieldException, ValidationException {

        Set<Order> orders = new HashSet<>();
        Set<Item> items = new HashSet<>();
        Set<Product> products = new HashSet<>();

        Category category = Category.builder()
                .name("Coffee")
                .build();


        Product product = Product.builder()
                .name("Long-black")
                .price(7.50)
                .isAvailable(true)
                .category(category)
                .build();

        Employee employee = Employee.builder()
                .login("Alex")
//                .passWord("2211")
//                .orders(orders)
                .role(Role.MANAGER)
                .build();


        Order order = Order.builder()
                .items(items)
                .build();

        Item item = Item.builder()
                .productQuantity(1)
                .product(product)
                .order(order)
                .build();


        orders.add(order);
        items.add(item);
        products.add(product);


        ProductMapper productMapper = new ProductMapper();
        CategoryMapper categoryMapper = new CategoryMapper(productMapper);
        ItemMapper itemMapper = new ItemMapper(productMapper);
        OrderMapper orderMapper = new OrderMapper(itemMapper);
        EmployeeMapper employeeMapper = new EmployeeMapper(orderMapper);
        ProductDAO dao = new ProductDAOImpl();
        ItemService itemService = new ItemServiceImpl();
        OrderDAO orderDAO = new OrderDAOImpl();
        EmployeeDAO employeeDAO = new EmployeeDAOImpl();



        ItemDAO itemDAO = new ItemDAOImpl();
        CategoryDAO categoryDAO = new CategoryDAOImpl();


        IdValidatorFactory idValidatorFactory  = new IdValidatorFactory();
        CategoryService categoryService = new CategoryServiceImpl(idValidatorFactory);
        BaseEmployeeValidator baseEmployeeValidator = new BaseEmployeeValidatorImpl();
        BusinessEmployeeValidator businessEmployeeValidator = new BusinessEmployeeValidatorImpl(baseEmployeeValidator,employeeDAO);
        BaseProductValidator baseProductValidator = new BaseProductValidatorImpl();
        ProductServiceImpl productService = new ProductServiceImpl(idValidatorFactory);
        OrderService orderService = new OrderServiceImpl(idValidatorFactory,productService);
        EmployeeService employeeService = new EmployeeServiceImpl(idValidatorFactory,orderService);
        idValidatorFactory.setEmployeeDAO(employeeDAO);
        idValidatorFactory.setCategoryDAO(categoryDAO);
        idValidatorFactory.setOrderDAO(orderDAO);
        idValidatorFactory.setItemDAO(itemDAO);
        idValidatorFactory.setCategoryDAO(categoryDAO);
        idValidatorFactory.setProductDAO(dao);


//        orderService.addProductToOrder(productMapper.mapToDTO(dao.get(35)),orderMapper.mapToDTO(orderDAO.get(40)),6);

//        orderService.buyOrder(orderMapper.mapToDTO(orderDAO.get(40)));
//        System.out.println(orderService.countAmountOfAllItems(orderMapper.mapToDTO(orderDAO.get(40))));

//        employeeService.addNewOrderToEmployee(employeeMapper.mapToDTO(employeeDAO.get(4)));

//        orderService.buyOrder(orderMapper.mapToDTO(orderDAO.get()));
        System.out.println(employeeService.getTotalAmountOfOrders());

    }
}
