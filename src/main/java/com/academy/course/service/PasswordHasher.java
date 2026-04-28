package com.academy.course.service;


import at.favre.lib.crypto.bcrypt.BCrypt;

public class PasswordHasher {

    public static String hashPass(String pass) {
        int logRounds = 12;
        return BCrypt.withDefaults().hashToString(logRounds,pass.toCharArray());
    }

    public static BCrypt.Result checkPass(String pass, String hash) {
        return BCrypt.verifyer().verify(pass.toCharArray(),hash);

    }
}
