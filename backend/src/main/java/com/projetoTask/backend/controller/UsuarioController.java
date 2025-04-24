package com.projetoTask.backend.controller;

import com.projetoTask.backend.dtos.LoginRequestDTO;
import com.projetoTask.backend.dtos.LoginResponseDTO;
import com.projetoTask.backend.dtos.UsuarioRegisterDTO;
import com.projetoTask.backend.services.ServiceUsuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    ServiceUsuario serviceUsuario;

    @PostMapping("/register")
    public String registerUser(@RequestBody @Valid UsuarioRegisterDTO data) {
        return serviceUsuario.salvarUsuario(data);
    }

    @PostMapping("/login")
    public LoginResponseDTO fazerLogin(@RequestBody @Valid LoginRequestDTO dataLogin) {
        return serviceUsuario.verificarLogin(dataLogin);
    }

}
