package lseg.example.prueba.service;

import lseg.example.prueba.model.Tasks;
import lseg.example.prueba.repository.TasksRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TasksServiceImpl implements TasksService{
    private final TasksRepository tasksRepository;

    public TasksServiceImpl(TasksRepository tasksRepository) {
        this.tasksRepository = tasksRepository;
    }

    @Override
    public Tasks addTask(Tasks task) {
        return tasksRepository.save(task);
    }

    @Override
    public Optional<Tasks> getTaskById(UUID uuid) {
        return tasksRepository.findById(uuid);
    }

    @Override
    public Page<Tasks> getAllTasks(Pageable pageable) {
        return tasksRepository.findAll(pageable);
    }

    @Override
    public Tasks updateTaskById(UUID uuid, Tasks task) {
        Optional<Tasks> taskToUpdate = tasksRepository.findById(uuid);
        if(taskToUpdate.isPresent()) {
            taskToUpdate.get().setTitle(task.getTitle());
            taskToUpdate.get().setDescription(task.getDescription());
            taskToUpdate.get().setDueDate(task.getDueDate());
            taskToUpdate.get().setStatus(task.getStatus());
            return tasksRepository.save(taskToUpdate.get());
        }
        return null;
    }

    @Override
    public void deleteTaskById(UUID uuid) {
        tasksRepository.deleteById(uuid);
    }

    @Override
    public boolean existsTaskById(UUID uuid) {
        return tasksRepository.existsById(uuid);
    }
}
