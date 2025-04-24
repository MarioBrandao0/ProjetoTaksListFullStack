package com.projetoTask.backend.util;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

public class VerificadorHeader {
    public static String verificarHeader(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");

        if(authHeader == null || !authHeader.startsWith("Bearer ")) {
            return null;
        }
        return authHeader.replace("Bearer ", "");
    }
}
