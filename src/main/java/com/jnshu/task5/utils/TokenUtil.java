package com.jnshu.task5.utils;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

public class TokenUtil {
    private static final  String TOKEN_LIST_NAME = "tokenList";
    public static final String TOKEN_STRING_NAME = "token";

    private  static ArrayList getTokenList(HttpSession session){
        Object obj = session.getAttribute(TOKEN_LIST_NAME);
        if (obj != null) {
            return (ArrayList) obj;
        } else  {
            ArrayList tokenList = new ArrayList();
            session.setAttribute(TOKEN_LIST_NAME, tokenList);
            return tokenList;
        }
    }

    private static void saveTokeString (String tokenStr, HttpSession session){
        ArrayList tokenList = getTokenList(session);
        tokenList.add(tokenStr);
        session.setAttribute(TOKEN_LIST_NAME,tokenList);
    }
    private static String generateTokenString(){
        return  new Long(System.currentTimeMillis()).toString();
    }

    public static String getTokenString(HttpSession session){
        String tokenStr = generateTokenString();
        saveTokeString(tokenStr, session);
        return tokenStr;
    }

    public static boolean isTokenStringValid(String tokenStr, HttpSession session){
        boolean valid = false;
        if (session != null){
            ArrayList tokenList = getTokenList(session);
            if (tokenList.contains(tokenStr)){
                valid = true ;
                tokenList.remove(tokenStr);
            }
        }
        return valid;
    }

}
