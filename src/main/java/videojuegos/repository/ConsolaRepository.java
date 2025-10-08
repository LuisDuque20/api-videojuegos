package videojuegos.repository;

import videojuegos.model.Consola;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsolaRepository extends JpaRepository<Consola, Long> {}