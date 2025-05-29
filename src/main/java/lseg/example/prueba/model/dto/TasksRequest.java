package lseg.example.prueba.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// * DTO
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TasksRequest {
    private String id;
    @NotNull
    private String title;
    private String description;
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}$", message = "La fecha debe tener el formato 'yyyy-MM-ddTHH:mm:ss'")
    private String dueDate;
    @NotNull
    @Pattern(regexp = "^(pending|completed|in-progress)$", message = "El estatus debe de ser uno de los siguientes estatus: 'completed', 'pending' o 'in-progress'")
    private String status;
}
