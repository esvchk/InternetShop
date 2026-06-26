package com.academy.course.utils;

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
import com.academy.course.mapper.ProductConverter;
import com.academy.course.mapper.factory.MapperFactory;
import com.academy.course.service.*;
import com.academy.course.service.validator.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class AppContextListener implements ServletContextListener {

    private static final Logger logger = LogManager.getLogger(AppContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent event) {

        EmployeeDAO employeeDAO = new EmployeeDAOImpl();
        CategoryDAO categoryDAO = new CategoryDAOImpl();
        ProductDAO productDAO = new ProductDAOImpl();
        ItemDAO itemDAO = new ItemDAOImpl();
        OrderDAO orderDAO = new OrderDAOImpl();

        IdValidatorFactory idValidatorFactory = new IdValidatorFactory(employeeDAO, productDAO,
                orderDAO, categoryDAO, itemDAO);

        BaseCategoryValidator baseCategoryValidator = new BaseCategoryValidatorImpl();
        BusinessCategoryValidator businessCategoryValidator = new BusinessCategoryValidatorImpl(categoryDAO);

        BaseEmployeeValidator baseEmployeeValidator = new BaseEmployeeValidatorImpl();
        BusinessEmployeeValidator businessEmployeeValidator =
                new BusinessEmployeeValidatorImpl(baseEmployeeValidator, employeeDAO);

        BusinessItemValidator businessItemValidator = new BusinessItemValidatorImpl(itemDAO, orderDAO);

        BaseOrderValidator baseOrderValidator = new BaseOrderValidatorImpl();
        BusinessOrderValidator businessOrderValidator = new BusinessOrderValidatorImpl(orderDAO,productDAO);

        BaseProductValidator baseProductValidator = new BaseProductValidatorImpl();
        BusinessProductValidator businessProductValidator = new BusinessProductValidatorImpl(baseProductValidator, productDAO);

        BasePaginationValidation basePaginationValidation = new BasePaginationValidationImpl();




        ProductService productService = new ProductServiceImpl(
                productDAO
                , MapperFactory.getProductMapper()
                , idValidatorFactory
                , baseProductValidator
                , businessProductValidator
                ,basePaginationValidation
                );

        ItemService itemService = new ItemServiceImpl(
                itemDAO
                , orderDAO
                , MapperFactory.getItemMapperMapper()
                , idValidatorFactory
                , businessItemValidator);

        OrderService orderService = new OrderServiceImpl(
                orderDAO
                , productDAO
                , itemDAO
                , MapperFactory.getProductMapper()
                , MapperFactory.getItemMapperMapper()
                , MapperFactory.getOrderMapper()
                , idValidatorFactory
                , baseOrderValidator
                , businessOrderValidator
                , businessItemValidator
                , productService
                , itemService);

        EmployeeService employeeService = new EmployeeServiceImpl(
                employeeDAO
                , baseEmployeeValidator
                , MapperFactory.getEmployeeMapper()
                , idValidatorFactory
                , orderService
                , businessEmployeeValidator
                , basePaginationValidation
                , MapperFactory.getOrderMapper());

        CategoryService categoryService = new CategoryServiceImpl(
                categoryDAO
                , productDAO
                , MapperFactory.getCategoryMapper()
                , businessCategoryValidator
                , baseCategoryValidator
                , idValidatorFactory);

        TablesService tablesService = new TablesServiceImpl(productService,employeeService);


        ServletContext context = event.getServletContext();

        context.setAttribute("productService", productService);
        context.setAttribute("itemService", itemService);
        context.setAttribute("orderService", orderService);
        context.setAttribute("employeeService", employeeService);
        context.setAttribute("categoryService", categoryService);
        context.setAttribute("tablesService",tablesService);

        logger.info("Initialization of all services successful");

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.info("Destroying of all services successful");
    }
}
