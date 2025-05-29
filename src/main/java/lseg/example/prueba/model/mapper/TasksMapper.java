package lseg.example.prueba.model.mapper;

import lseg.example.prueba.model.Tasks;
import lseg.example.prueba.model.dto.TasksRequest;
import lseg.example.prueba.model.dto.TasksResponse;
import lseg.example.prueba.util.enums.Status;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TasksMapper {

    public Tasks convertToTasks(TasksRequest taskDto){
        Tasks.TasksBuilder aux = Tasks.builder().title(taskDto.getTitle()).status(Status.fromValue(taskDto.getStatus()));
        if(taskDto.getDescription() != null){
            aux.description(taskDto.getDescription());
        }
        if(taskDto.getDueDate() != null){
            aux.dueDate(LocalDateTime.parse(taskDto.getDueDate()));
        }
        return aux.build();
    }

    public TasksResponse convertToTasksResponse(Tasks tasks){
        TasksResponse.TasksResponseBuilder aux = TasksResponse.builder().id(tasks.getId().toString()).title(tasks.getTitle())
                .description(tasks.getDescription()).status(tasks.getStatus().toString());
        if(tasks.getDueDate() != null){
            aux.dueDate(tasks.getDueDate().toString());
        }
        return aux.build();
    }
}
