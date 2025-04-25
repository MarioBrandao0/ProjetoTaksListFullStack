package com.projetoTask.backend.services;

import com.projetoTask.backend.domains.Tasks;
import com.projetoTask.backend.domains.Usuario;
import com.projetoTask.backend.dtos.TaskDTO;
import com.projetoTask.backend.repository.TaskRepository;
import com.projetoTask.backend.repository.UsuarioRepository;
import com.projetoTask.backend.security.JwtUtil;
import com.projetoTask.backend.util.VerificadorHeader;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class ServiceTask {
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private JwtUtil jwtUtil;

    public ResponseEntity<?> registerTask(TaskDTO data, HttpServletRequest request) {
        if(!data.nomeTask().isBlank() && !data.descricao().isBlank()) {
            Usuario idUsuario = usuarioRepository.findById(jwtUtil.extractId(VerificadorHeader.verificarHeader(request))).orElseThrow(() -> new RuntimeException("Usuario não encontrado"));
            Tasks tasks = new Tasks(data, idUsuario);
            taskRepository.save(tasks);
            return ResponseEntity.ok("Tarefa Adicionada com Sucesso");
        }
        return ResponseEntity.ok("Verifique todos os campos");
    }

    public ResponseEntity<?> findTasks(HttpServletRequest request) {
        Long usuarioId = jwtUtil.extractId(VerificadorHeader.verificarHeader(request));
        return ResponseEntity.ok(taskRepository.findByUsuarioId(usuarioId));
    }

    @Transactional
    public ResponseEntity<?> doneTask(Long id) {
        Optional<Tasks> task = taskRepository.findById(id);

        if(task.isEmpty()) {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarefa não encontrada");
        }
        taskRepository.updateStatus(id);

        return ResponseEntity.ok("");
    }


}
