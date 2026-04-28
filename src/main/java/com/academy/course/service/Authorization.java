package com.academy.course.service;

import com.academy.course.dto.CustomerDTO;

public class Authorization {

    CustomerService customerService = new CustomerServiceImpl();

//    public CustomerDTO login(String login, String pass) {
//            if (PasswordHasher.checkPass(pass, userService.readPassByLogin(login))) {
//                if (user != null) {
//                    return UserConverter.convertUserToUserDTO(user);
//                }
//            }
//
//        return new UserDTO();
//    }
}
