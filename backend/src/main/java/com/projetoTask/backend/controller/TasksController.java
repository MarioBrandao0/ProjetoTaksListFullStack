package com.projetoTask.backend.controller;


import com.projetoTask.backend.dtos.TaskDTO;
import com.projetoTask.backend.repository.TaskRepository;
import com.projetoTask.backend.services.ServiceTask;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("tarefas")
public class TasksController {

    @Autowired
    TaskRepository taskRepository;


    @Autowired
    ServiceTask serviceTask;


    @PostMapping("/register")
    public ResponseEntity<?> registrarTarefa(@RequestBody @Valid TaskDTO data, HttpServletRequest request) {
        return serviceTask.registerTask(data, request);
    }

    @GetMapping("/recuperarTasks")
    public ResponseEntity<?> recuperarTasks(HttpServletRequest request) {
        return serviceTask.findTasks(request);
    }

    @DeleteMapping("/deletarTask/{id}")
    public void deletarTask(@PathVariable Long id) {
        taskRepository.deleteById(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> doneTask(@PathVariable @Valid Long id) {
        return serviceTask.doneTask(id);
    }

}
