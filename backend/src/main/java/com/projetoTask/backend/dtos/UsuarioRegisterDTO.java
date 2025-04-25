package com.projetoTask.backend.dtos;

import jakarta.validation.constraints.Email;

public record UsuarioRegisterDTO(String nome, @Email(message = "Formato de email invalido") String email, String senha) {
}
