package com.projetoTask.backend.domains;

import com.projetoTask.backend.dtos.UsuarioRegisterDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

@Table(name = "usuarios")
@Entity
@Getter
@Setter
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(unique = true)
    private String email;

    private String senha;

    public Usuario() {}

    public Usuario(UsuarioRegisterDTO dataUsuario) {
        this.nome = dataUsuario.nome();
        this.email = dataUsuario.email();
        this.senha = dataUsuario.senha();
    }

}
