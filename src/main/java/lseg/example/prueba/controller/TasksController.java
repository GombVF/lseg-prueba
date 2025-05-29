package lseg.example.prueba.controller;

import jakarta.validation.Valid;
import lseg.example.prueba.model.Tasks;
import lseg.example.prueba.model.dto.TasksRequest;
import lseg.example.prueba.model.mapper.TasksMapper;
import lseg.example.prueba.service.TasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("tasks")
public class TasksController {
    @Autowired
    private TasksService tasksService;

    @Autowired
    private TasksMapper tasksMapper;

    @PostMapping
    public ResponseEntity<?> createTask(@Valid @RequestBody TasksRequest task, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getFieldError().getDefaultMessage();
            return ResponseEntity.badRequest().body(Map.of("error", errorMessage));
        }
        Tasks tasks = tasksMapper.convertToTasks(task);
        tasksService.addTask(tasks);
        URI location = URI.create("/tasks/" + tasks.getId().toString());
        return ResponseEntity.created(location).body(tasksMapper.convertToTasksRequest(tasks));
    }

    @GetMapping
    public ResponseEntity<List<TasksRequest>> getAllTasks() {
        List<TasksRequest> allTasks = tasksService.getAllTasks().stream().map(tasksMapper::convertToTasksRequest).toList();
        return ResponseEntity.ok(allTasks);
    }

    @GetMapping("{id}")
    public ResponseEntity<TasksRequest> getTaskById(@PathVariable UUID id) {
        Optional<Tasks> tasks = tasksService.getTaskById(id);
        return tasks.map(task -> ResponseEntity.ok(tasksMapper.convertToTasksRequest(task)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateTask(@PathVariable UUID id, @Valid @RequestBody TasksRequest task, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getFieldError().getDefaultMessage();
            return ResponseEntity.badRequest().body(Map.of("error", errorMessage));
        }
        if(!tasksService.existsTaskById(id)){
            return ResponseEntity.notFound().build();
        }
        Tasks tasks = tasksMapper.convertToTasks(task);
        tasks = tasksService.updateTaskById(id, tasks);
        TasksRequest updatedTask = tasksMapper.convertToTasksRequest(tasks);
        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping("{id}")
    public void deleteTask(@PathVariable UUID id) {
        tasksService.deleteTaskById(id);
    }
}
