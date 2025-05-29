package lseg.example.prueba.repository;

import lseg.example.prueba.model.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TasksRepository extends JpaRepository<Tasks, UUID> {
}
