package com.jnshu.task5.beans;

import com.jnshu.task5.utils.Md5Util;
import org.springframework.stereotype.Component;

@Component

public interface TokenSetting {
    public static String TOKEN_KEY = "jnshu";
    public static String TOKEN_SALT = Md5Util.digest("123");
    public static final int COOKIE_MAX_AGE=7*24*3600;


}
