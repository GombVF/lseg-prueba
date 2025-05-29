package lseg.example.prueba.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lseg.example.prueba.util.converter.StatusConverter;
import lseg.example.prueba.util.enums.Status;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Clase Tasks relacionada a la tabla 'tasks' en la base de datos")
public class Tasks {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(description = "Identificador de tipo UUID de las tareas", example = "e8a98ab1-0fc9-4d59-9d18-0ca8335d413c")
    private UUID id;
    @Column(nullable=false, length=50)
    @Schema(description = "Titulo de la tarea", example = "Creación del CRUD del API", required = true)
    private String title;
    @Schema(description = "Descripción de la tarea", example = "Creación de los endpoints GET, POST, PUT y DELETE para un CRUD funcional del API")
    private String description;
    @Column(name="due_date")
    @Schema(description = "Fecha de Vencimiento", example = "2025-05-30T12:00:00")
    private LocalDateTime dueDate;
    @Convert(converter = StatusConverter.class)
    @Schema(description = "Estatus de la tarea", example = "ENUM('pending', 'completed', 'in-progress')", required = true)
    private Status status;
}
