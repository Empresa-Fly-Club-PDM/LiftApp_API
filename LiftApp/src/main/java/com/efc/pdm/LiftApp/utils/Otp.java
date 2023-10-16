package com.efc.pdm.LiftApp.utils;

import java.util.Random;

public class Otp {
    public String generateOTP(int len){
        String numbers ="0123456789";
        Random rndm_method = new Random();
        StringBuilder sb = new StringBuilder(len);
        for(int i = 0; i < len; i++){
            sb.append(numbers.charAt(rndm_method.nextInt(numbers.length())));
        }
        return sb.toString();
    }
}
//test