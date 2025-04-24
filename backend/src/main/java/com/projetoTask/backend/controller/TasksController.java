package com.projetoTask.backend.controller;

import com.projetoTask.backend.domains.Tasks;
import com.projetoTask.backend.domains.Usuario;
import com.projetoTask.backend.dtos.RecuperarTasksDTO;
import com.projetoTask.backend.dtos.TaskDTO;
import com.projetoTask.backend.repository.TaskRepository;
import com.projetoTask.backend.repository.UsuarioRepository;
import com.projetoTask.backend.security.JwtUtil;
import com.projetoTask.backend.util.VerificadorHeader;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("tarefas")
public class TasksController {
    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    JwtUtil jwtUtil;


    @PostMapping("/register")
    public ResponseEntity<String> registrarTarefa(@RequestBody @Valid TaskDTO data, HttpServletRequest request) {
        if(!data.nomeTask().isBlank() && !data.descricao().isBlank()) {
            Usuario idUsuario = usuarioRepository.findById(jwtUtil.extractId(VerificadorHeader.verificarHeader(request))).orElseThrow(() -> new RuntimeException("Usuario não encontrado"));
            Tasks tasks = new Tasks(data, idUsuario);
            taskRepository.save(tasks);
            return ResponseEntity.ok("Tarefa Adicionada com Sucesso");
        }
        return ResponseEntity.ok("Verifique todos os campos");
    }

    @GetMapping("/recuperarTasks")
    public ResponseEntity<?> recuperarTasks(HttpServletRequest request) {
        Long usuarioId = jwtUtil.extractId(VerificadorHeader.verificarHeader(request));
        return ResponseEntity.ok(taskRepository.findByUsuarioId(usuarioId));
    }

    @DeleteMapping("/deletarTask/{id}")
    public void deletarTask(@PathVariable @Valid Long id) {
        taskRepository.deleteById(id);
    }

}
