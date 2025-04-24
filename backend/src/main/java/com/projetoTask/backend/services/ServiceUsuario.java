package com.projetoTask.backend.services;

import com.projetoTask.backend.domains.Usuario;
import com.projetoTask.backend.dtos.LoginRequestDTO;
import com.projetoTask.backend.dtos.LoginResponseDTO;
import com.projetoTask.backend.dtos.UsuarioRegisterDTO;
import com.projetoTask.backend.repository.UsuarioRepository;
import com.projetoTask.backend.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServiceUsuario {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private JwtUtil jwtUtil;

    public String salvarUsuario(UsuarioRegisterDTO data) {
        try {
            if (data.nome() != null && data.email() != null && data.senha() != null) {
                Usuario usuario = new Usuario(data);
                usuarioRepository.save(usuario);
                return "Usuario cadastrado com sucesso";
            }
            return "Verifique todos os campos";
        }
        catch (Exception e) {
            return "Erro ao criar o cadastro";
        }

    }


    public LoginResponseDTO verificarLogin(LoginRequestDTO dataLogin) {
        if(!dataLogin.email().isBlank() && !dataLogin.senha().isBlank()) {
            Optional<Usuario> usuario = usuarioRepository.findByEmailAndSenha(dataLogin.email(), dataLogin.senha());

            if(usuario.isPresent()) {
                return new LoginResponseDTO(true, jwtUtil.generateToken(usuario.get().getId(), usuario.get().getNome()));
            }
        }


        return new LoginResponseDTO(false, null);

    }

}
