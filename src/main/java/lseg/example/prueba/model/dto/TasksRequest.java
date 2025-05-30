package lseg.example.prueba.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Clase DTO para las peticiones relacionada a las Tasks")
public class TasksRequest {
    @NotNull
    @Schema(description = "Titulo de la tarea", example = "Creación del CRUD del API", required = true)
    private String title;
    @Schema(description = "Descripción de la tarea", example = "Creación de los endpoints GET, POST, PUT y DELETE para un CRUD funcional del API")
    private String description;
    @Pattern(regexp = "^\\d{4}-[0,1][0-9]-([0-2][0-9]|3[0,1])T[0-5][0-9]:[0-5][0-9]:[0-5][0-9]$", message = "La fecha debe tener el formato 'yyyy-MM-ddTHH:mm:ss'")
    @Schema(description = "Fecha de Vencimiento", example = "2025-05-30T12:00:00")
    private String dueDate;
    @NotNull
    @Pattern(regexp = "^(pending|completed|in-progress)$", message = "El estatus debe de ser uno de los siguientes estatus: 'completed', 'pending' o 'in-progress'")
    @Schema(description = "Estatus de la tarea", example = "ENUM('pending', 'completed', 'in-progress')", required = true)
    private String status;
}
