package lseg.example.prueba.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lseg.example.prueba.model.Tasks;
import lseg.example.prueba.model.dto.TasksErrorResponse;
import lseg.example.prueba.model.dto.TasksRequest;
import lseg.example.prueba.model.dto.TasksResponse;
import lseg.example.prueba.model.mapper.TasksMapper;
import lseg.example.prueba.service.TasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("tasks")
public class TasksController {
    @Autowired
    private TasksService tasksService;

    @Autowired
    private TasksMapper tasksMapper;

    @Operation(summary = "Creación de una tarea")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Tarea creada correctamente",
                    content = @Content(schema = @Schema(implementation = TasksResponse.class))),
            @ApiResponse(responseCode = "400", description = "No se ha podido crear la tarea debido a algún error en la petición", content = @Content(schema = @Schema(implementation = TasksErrorResponse.class)))
    })
    @PostMapping
    public ResponseEntity<?> createTask(@Valid @RequestBody TasksRequest task, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getFieldError().getDefaultMessage();
            TasksErrorResponse tasksErrorResponse = TasksErrorResponse.builder().message(errorMessage).statusCode("400").timestamp(LocalDateTime.now()).build();
            return ResponseEntity.badRequest().body(tasksErrorResponse);
        }
        Tasks tasks = tasksMapper.convertToTasks(task);
        tasksService.addTask(tasks);
        URI location = URI.create("/tasks/" + tasks.getId().toString());
        return ResponseEntity.created(location).body(tasksMapper.convertToTasksResponse(tasks));
    }

    @Operation(summary = "Obtención de todas las tareas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tareas consultadas correctamente", content = @Content(schema = @Schema(implementation = List.class))),
    })
    @GetMapping
    public ResponseEntity<Page<TasksResponse>> getAllTasks(@PageableDefault Pageable pageable) {
        Page<TasksResponse> allTasks = tasksService.getAllTasks(pageable).map(tasksMapper::convertToTasksResponse);
        return ResponseEntity.ok(allTasks);
    }

    @Operation(summary = "Obtención de una tarea dado su UUID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se ha consultado la tarea deseada correctamente", content = @Content(schema = @Schema(implementation = TasksResponse.class))),
            @ApiResponse(responseCode = "404", description = "La tarea solicitada no existe")
    })
    @GetMapping("{id}")
    public ResponseEntity<TasksResponse> getTaskById(@PathVariable UUID id) {
        Optional<Tasks> tasks = tasksService.getTaskById(id);
        return tasks.map(task -> ResponseEntity.ok(tasksMapper.convertToTasksResponse(task)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Actualización de una tarea en específico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se ha actualizado la tarea deseada correctamente", content = @Content(schema = @Schema(implementation = TasksResponse.class))),
            @ApiResponse(responseCode = "400", description = "No se ha podido crear la tarea debido a algún error en la petición", content = @Content(schema = @Schema(implementation = TasksErrorResponse.class)))
    })
    @PutMapping("{id}")
    public ResponseEntity<?> updateTask(@PathVariable UUID id, @Valid @RequestBody TasksRequest task, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getFieldError().getDefaultMessage();
            TasksErrorResponse tasksErrorResponse = TasksErrorResponse.builder().message(errorMessage).statusCode("400").timestamp(LocalDateTime.now()).build();
            return ResponseEntity.badRequest().body(tasksErrorResponse);
        }
        if(!tasksService.existsTaskById(id)){
            return ResponseEntity.notFound().build();
        }
        Tasks tasks = tasksMapper.convertToTasks(task);
        tasks = tasksService.updateTaskById(id, tasks);
        TasksResponse updatedTask = tasksMapper.convertToTasksResponse(tasks);
        return ResponseEntity.ok(updatedTask);
    }

    @Operation(summary = "Eliminación de una tarea")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se ha eliminado correctamente la tarea")
    })
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteTask(@PathVariable UUID id) {
        tasksService.deleteTaskById(id);
        return ResponseEntity.ok().build();
    }
}
