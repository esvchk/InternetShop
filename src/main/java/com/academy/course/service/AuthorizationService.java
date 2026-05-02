package com.academy.course.service;

import com.academy.course.dto.CustomerDTO;

public class AuthorizationService {

    private final CustomerService customerService = new CustomerServiceImpl();


    public boolean login(String login, String pass) throws NoSuchFieldException {
        CustomerDTO customerDTO = customerService.findCustomerByLogin(login);

        if (PasswordHasher.checkPass(pass, customerService.getPassOfCustomerByLogin(login))) {
            if (customerDTO != null) {
                return false;
            } else
                return true;
        } else
            return false;
    }
}
