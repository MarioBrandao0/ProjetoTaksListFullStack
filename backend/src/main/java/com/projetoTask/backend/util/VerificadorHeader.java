package com.projetoTask.backend.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

public class VerificadorHeader {
    public static String getTokenFromCookie(HttpServletRequest request) {
        System.out.println(request.getCookies());
        if(request.getCookies() != null) {
            for(Cookie cookie : request.getCookies()) {
                System.out.println("Cookie recebido: " + cookie.getName());
            }
        }
        return null;
    }
}
