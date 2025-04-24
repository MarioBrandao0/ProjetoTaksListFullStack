package com.projetoTask.backend.repository;

import com.projetoTask.backend.domains.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Tasks, Long> {

    List<Tasks> findByUsuarioId(Long usuarioId);

}
