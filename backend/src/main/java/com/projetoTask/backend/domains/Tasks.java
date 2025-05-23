package com.projetoTask.backend.domains;

import com.projetoTask.backend.dtos.TaskDTO;
import jakarta.persistence.*;
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
