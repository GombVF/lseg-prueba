package lseg.example.prueba.service;

import lseg.example.prueba.model.Tasks;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TasksService {
    Tasks addTask(Tasks task);
    Optional<Tasks> getTaskById(UUID uuid);
    Page<Tasks> getAllTasks(Pageable pageable);
    Tasks updateTaskById(UUID uuid, Tasks task);
    void deleteTaskById(UUID uuid);
    boolean existsTaskById(UUID uuid);
}
