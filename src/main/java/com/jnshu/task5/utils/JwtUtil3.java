package com.jnshu.task5.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil3 {
    public static String sign(Map<String, Object> payload, Date expiration, String key){
        return Jwts.builder()
                .setClaims(payload)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, key.getBytes())
                .compact();
    }

    public static Map verify(String token, String key){
        try{
            return Jwts.parser()
                    .setSigningKey(key.getBytes())
                    .parseClaimsJws(token)
                    .getBody();
        }catch (Exception e){
            return null;
        }
    }

    public static void main(String[] args) {
        Map map = new HashMap();
        map.put("name","123");
        map.put("pwd","321");

        long currentTime = System.currentTimeMillis();
        Date expiration = new Date(currentTime + 600*1000);


        String token = JwtUtil3.sign(map,expiration,"asdf");
        System.out.println("token = " + token);
        Map remap = new HashMap();
        remap = JwtUtil3.verify(token,"asdf");
        System.out.println("map + " + map);
    }
}
