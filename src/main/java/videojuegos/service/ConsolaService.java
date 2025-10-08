package videojuegos.service;


import videojuegos.model.Consola;
import org.springframework.stereotype.Service;
import videojuegos.repository.ConsolaRepository;
import java.util.List;
import java.util.Optional;

@Service
public class ConsolaService {
    private final ConsolaRepository repo;
    public ConsolaService(ConsolaRepository repo) { this.repo = repo; }
    public List<Consola> findAll() { return repo.findAll(); }
    public Optional<Consola> findById(Long id) { return repo.findById(id); }
    public Consola save(Consola c) { return repo.save(c); }
    public void delete(Long id) { repo.deleteById(id); }
}
