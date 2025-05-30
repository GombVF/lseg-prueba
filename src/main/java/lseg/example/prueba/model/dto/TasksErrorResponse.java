package lseg.example.prueba.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

// * DTO
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Clase DTO para las respuestas de error de las Tasks")
public class TasksErrorResponse {
    @Schema(description = "Estatus HTTP del error generado", example = "400")
    private String statusCode;
    @Schema(description = "Mensaje de error generado.", example = "La fecha debe tener el formato 'yyyy-MM-ddTHH:mm:ss'")
    private String message;
    @Schema(description = "Marca de tiempo del error generado", example = "2025-05-29T14:33:22")
    private LocalDateTime timestamp;
}
