package lseg.example.prueba.service;

import lseg.example.prueba.model.Tasks;
import lseg.example.prueba.repository.TasksRepository;
import lseg.example.prueba.util.enums.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TasksServiceImplTest {

    @Mock
    private TasksRepository taskRepository;

    @InjectMocks
    private TasksServiceImpl taskService;


    private UUID taskId;
    private Tasks mockTask;

    @BeforeEach
    void setUp() {
        taskId = UUID.randomUUID();
        mockTask = Tasks.builder().id(taskId).title("Titulo de Tarea").build();
    }


    @Test
    void addTaskMinimumRequired() {
        Tasks newTask = Tasks.builder().id(UUID.randomUUID()).title("Titulo de Tarea").status(Status.PENDING).build();
        when(taskRepository.save(mockTask)).thenReturn(newTask);
        Tasks task = taskService.addTask(mockTask);
        assertNotNull(task);
        assertNotNull(task.getId());
        assertEquals("Titulo de Tarea", task.getTitle());
    }

    @Test
    void getTaskByIdReturningTask() {
        when(taskRepository.findById(taskId)).thenReturn(Optional.of(mockTask));
        Tasks result = taskService.getTaskById(taskId).orElse(null);
        assertNotNull(result);
        assertEquals("Titulo de Tarea", result.getTitle());
    }

    @Test
    void getTaskByIdReturningNullTask() {
        when(taskRepository.findById(taskId)).thenReturn(Optional.empty());
        Tasks result = taskService.getTaskById(taskId).orElse(null);
        assertNull(result);
    }

    @Test
    void updateTaskById() {
        Tasks taskToSave = Tasks.builder().id(mockTask.getId()).title(mockTask.getTitle()).description("Se ha agregado una descripción").build();
        when(taskRepository.findById(taskId)).thenReturn(Optional.of(mockTask));
        when(taskRepository.save(taskToSave)).thenReturn(taskToSave);
        Tasks taskBeforeUpdate = taskService.getTaskById(taskId).map(task -> Tasks.builder().id(task.getId())
                .title(task.getTitle()).description(task.getDescription()).dueDate(task.getDueDate())
                .status(task.getStatus()).build()).orElse(null);
        Tasks taskAfterUpdate = taskService.updateTaskById(taskId, taskToSave);
        assertNull(taskBeforeUpdate.getDescription());
        assertNotNull(taskAfterUpdate);
        assertNotNull(taskAfterUpdate.getDescription());
        assertEquals(taskBeforeUpdate.getId(), taskAfterUpdate.getId());
    }


    @Test
    void existsTaskById() {
        when(taskRepository.existsById(taskId)).thenReturn(true);
        boolean result = taskService.existsTaskById(taskId);
        assertTrue(result);
    }

    @Test
    void existsNotTaskById() {
        when(taskRepository.existsById(taskId)).thenReturn(false);
        boolean result = taskService.existsTaskById(taskId);
        assertFalse(result);
    }
}