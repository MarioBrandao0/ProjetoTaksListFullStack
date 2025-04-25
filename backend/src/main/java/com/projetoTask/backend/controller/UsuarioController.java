package com.projetoTask.backend.controller;

import com.projetoTask.backend.dtos.LoginRequestDTO;
import com.projetoTask.backend.dtos.LoginResponseDTO;
import com.projetoTask.backend.dtos.UsuarioRegisterDTO;
import com.projetoTask.backend.security.JwtUtil;
import com.projetoTask.backend.services.ServiceUsuario;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    ServiceUsuario serviceUsuario;

    @Autowired
    JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody @Valid UsuarioRegisterDTO data, BindingResult result) {
        if(result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors().get(0).getDefaultMessage());
        }
        return serviceUsuario.salvarUsuario(data);
    }

    @PostMapping("/apagar")
    public ResponseEntity<?> apagarConta(HttpServletRequest request) {
        return serviceUsuario.deletarUsuario(request);
    }

    @PostMapping("/login")
    public ResponseEntity<?> fazerLogin(@RequestBody @Valid LoginRequestDTO dataLogin) {
        return serviceUsuario.verificarLogin(dataLogin);
    }

}
