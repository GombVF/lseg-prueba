package lseg.example.prueba.service;

import lseg.example.prueba.model.Tasks;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TasksService {
    Tasks addTask(Tasks task);
    Optional<Tasks> getTaskById(UUID uuid);
    List<Tasks> getAllTasks();
    Tasks updateTaskById(UUID uuid, Tasks task);
    void deleteTaskById(UUID uuid);
    boolean existsTaskById(UUID uuid);
}
