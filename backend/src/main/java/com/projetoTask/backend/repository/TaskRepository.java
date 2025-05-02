package com.projetoTask.backend.repository;

import com.projetoTask.backend.domains.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Tasks, Long> {

    List<Tasks> findByUsuarioId(Long usuarioId);

    void deleteByUsuarioId(Long usuarioId);

    @Modifying
    @Query("UPDATE Tasks t SET t.status = :status WHERE t.id = :id")
    void updateStatus(@Param("id") Long id, @Param("status") boolean status);
}
