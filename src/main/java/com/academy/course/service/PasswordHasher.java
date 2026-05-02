package com.academy.course.service;


import at.favre.lib.crypto.bcrypt.BCrypt;

public class PasswordHasher {

    public static String hashPass(String pass) {
        int logRounds = 12;
        return BCrypt.withDefaults().hashToString(logRounds,pass.toCharArray());
    }

    public static boolean checkPass(String pass, String hash) throws NoSuchFieldException {
        BCrypt.Result result = BCrypt.verifyer().verify(pass.toCharArray(),hash);
        return result.verified;


    }
}
