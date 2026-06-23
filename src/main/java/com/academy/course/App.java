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
import com.academy.course.dto.ProductDTO;
import com.academy.course.mapper.*;
import com.academy.course.model.*;
import com.academy.course.paginator.PaginatedResult;
import com.academy.course.service.*;
import com.academy.course.service.validator.*;
import com.academy.course.utils.Discount;
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

        OrderDAO orderDAO = new OrderDAOImpl();
        EmployeeDAO employeeDAO = new EmployeeDAOImpl();



        ItemDAO itemDAO = new ItemDAOImpl();
        CategoryDAO categoryDAO = new CategoryDAOImpl();

        BaseProductValidator baseProductValidator = new BaseProductValidatorImpl();
        BusinessProductValidator businessProductValidator = new BusinessProductValidatorImpl(baseProductValidator,dao);
        BaseOrderValidator baseOrderValidator = new BaseOrderValidatorImpl();
        BusinessOrderValidator businessOrderValidator = new BusinessOrderValidatorImpl(orderDAO,dao);
        BusinessItemValidator businessItemValidator = new BusinessItemValidatorImpl(itemDAO,orderDAO);
        BasePaginationValidation basePaginationValidation = new BasePaginationValidationImpl();
        BaseEmployeeValidator baseEmployeeValidator = new BaseEmployeeValidatorImpl();
        BusinessEmployeeValidator businessEmployeeValidator = new BusinessEmployeeValidatorImpl(baseEmployeeValidator,employeeDAO);



        IdValidatorFactory idValidatorFactory  = new IdValidatorFactory();
        idValidatorFactory.setEmployeeDAO(employeeDAO);
        idValidatorFactory.setCategoryDAO(categoryDAO);
        idValidatorFactory.setOrderDAO(orderDAO);
        idValidatorFactory.setItemDAO(itemDAO);
        idValidatorFactory.setCategoryDAO(categoryDAO);
        idValidatorFactory.setProductDAO(dao);


        ProductService productService = new ProductServiceImpl(dao,productMapper,idValidatorFactory
                ,baseProductValidator,businessProductValidator,basePaginationValidation);

        ItemService itemService = new ItemServiceImpl(itemDAO,orderDAO,itemMapper,idValidatorFactory,businessItemValidator);

        OrderService orderService = new OrderServiceImpl(orderDAO,dao,itemDAO,
                productMapper,itemMapper,orderMapper,idValidatorFactory,
                baseOrderValidator,businessOrderValidator,businessItemValidator,productService,itemService);

        EmployeeService employeeService = new EmployeeServiceImpl(employeeDAO,baseEmployeeValidator,
                employeeMapper,idValidatorFactory,orderService,
                businessEmployeeValidator,basePaginationValidation,orderMapper);

        TablesService tablesService = new TablesServiceImpl(productService,employeeService);
        System.out.println(tablesService.getPairedList("Alex"));

//        orderService.addProductToOrder(4,6,2);


//        ProductService productService = new ProductServiceImpl(dao,productMapper,idValidatorFactory,baseProductValidator,businessProductValidator);

//        System.out.println(productService.getAllProducts());


    }
}
