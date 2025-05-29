package lseg.example.prueba.model;

import jakarta.persistence.*;
import lseg.example.prueba.util.enums.Status;

import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name="tasks")
public class Tasks {
    @Id
    @GeneratedValue
    private UUID id;
    @Column(nullable=false, length=50)
    private String title;
    private String description;
    @Column(name="due_date")
    private OffsetDateTime dueDate;
    private Status status;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public OffsetDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(OffsetDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Tasks tasks = (Tasks) o;
        return Objects.equals(id, tasks.id) && Objects.equals(title, tasks.title) && Objects.equals(description, tasks.description) && Objects.equals(dueDate, tasks.dueDate) && status == tasks.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, dueDate, status);
    }

    @Override
    public String toString() {
        return "Tasks{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", dueDate=" + dueDate +
                ", status=" + status +
                '}';
    }
}
