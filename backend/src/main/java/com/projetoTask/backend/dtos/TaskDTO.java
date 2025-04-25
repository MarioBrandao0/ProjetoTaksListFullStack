package com.projetoTask.backend.dtos;

import jakarta.validation.constraints.NotBlank;

public record TaskDTO(@NotBlank(message = "Coloque um nome na task") String nomeTask, String descricao) {
}
