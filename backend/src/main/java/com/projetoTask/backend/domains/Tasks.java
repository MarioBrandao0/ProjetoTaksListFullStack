package com.projetoTask.backend.domains;

import com.projetoTask.backend.dtos.TaskDTO;
import com.projetoTask.backend.repository.UsuarioRepository;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;


@Table(name = "tasks")
@Entity
@Getter
@Setter
public class Tasks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Coloque um nome na task")
    private String nomeTask;
    private String descricao;
    private boolean status = false;

    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id", nullable = false)
    private Usuario usuario;

    public Tasks() {}

    public Tasks(TaskDTO data, Usuario idUsuario) {
        this.nomeTask = data.nomeTask();
        this.descricao = data.descricao();
        this.usuario = idUsuario;
    }



}
