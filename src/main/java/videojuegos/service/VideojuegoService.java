package videojuegos.service;

import videojuegos.model.Videojuego;
import org.springframework.stereotype.Service;
import videojuegos.repository.VideojuegoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class VideojuegoService {
    private final VideojuegoRepository repo;
    public VideojuegoService(VideojuegoRepository repo) { this.repo = repo; }
    public List<Videojuego> findAll() { return repo.findAll(); }
    public Optional<Videojuego> findById(Long id) { return repo.findById(id); }
    public Videojuego save(Videojuego v) { return repo.save(v); }
    public void delete(Long id) { repo.deleteById(id); }
}
