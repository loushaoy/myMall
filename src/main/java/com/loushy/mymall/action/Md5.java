package com.loushy.mymall.action;

import org.springframework.util.DigestUtils;

import java.nio.charset.Charset;

public class Md5 {
    public static String setMd5(String str){
       return DigestUtils.md5DigestAsHex(str.getBytes(Charset.forName("UTF-8")));
    }

    public static Boolean checkPassword(String password1,String password2){
        if (setMd5(password1).equals(password2)){
            return true;
        }
        return false;
    }
}
