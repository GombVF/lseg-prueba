package lseg.example.prueba.model;

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
public class Tasks {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable=false, length=50)
    private String title;
    private String description;
    @Column(name="due_date")
    private LocalDateTime dueDate;
    @Convert(converter = StatusConverter.class)
    private Status status;
}
