package com.projetoTask.backend.services;

import com.projetoTask.backend.domains.Usuario;
import com.projetoTask.backend.dtos.LoginRequestDTO;
import com.projetoTask.backend.dtos.LoginResponseDTO;
import com.projetoTask.backend.dtos.UsuarioRegisterDTO;
import com.projetoTask.backend.repository.TaskRepository;
import com.projetoTask.backend.repository.UsuarioRepository;
import com.projetoTask.backend.security.JwtUtil;
import com.projetoTask.backend.util.VerificadorHeader;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Optional;

@Service
public class ServiceUsuario {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private JwtUtil jwtUtil;

    public ServiceUsuario(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public ResponseEntity<?> salvarUsuario(UsuarioRegisterDTO data) {
        try {
            if (data.nome() != null && data.email() != null && data.senha() != null) {
                Usuario usuario = new Usuario(data);
                usuarioRepository.save(usuario);
                return ResponseEntity.ok("Usuario cadastrado com sucesso");
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Verifique todos os campos");
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao criar o cadastro");
        }

    }


    public ResponseEntity<?> verificarLogin(LoginRequestDTO dataLogin) {
        if(!dataLogin.email().isBlank() && !dataLogin.senha().isBlank()) {
            Optional<Usuario> usuario = usuarioRepository.findByEmailAndSenha(dataLogin.email(), dataLogin.senha());

            if(usuario.isPresent()) {
                String token = jwtUtil.generateToken(usuario.get().getId(), usuario.get().getNome());
                ResponseCookie cookie = ResponseCookie.from("token", token)
                        .httpOnly(true)
                        .secure(false)
                        .path("/")
                        .sameSite("Lax")
                        .domain("localhost")
                        .maxAge(Duration.ofHours(1))
                        .build();

                return ResponseEntity.ok()
                        .header(HttpHeaders.SET_COOKIE, cookie.toString())
                        .body(new LoginResponseDTO(true));
            }
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new LoginResponseDTO(false));

    }

    public ResponseEntity<?> deletarUsuario(HttpServletRequest request) {
        Long idUsuario = jwtUtil.extractId(VerificadorHeader.getTokenFromCookie(request));
        if(usuarioRepository.existsById(idUsuario)) {
            usuarioRepository.deleteById(idUsuario);
            taskRepository.deleteByUsuarioId(idUsuario);
            return ResponseEntity.ok("Conta deletada com sucesso");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado");

    }

}
