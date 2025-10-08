package videojuegos.service;

import videojuegos.model.Venta;
import videojuegos.model.Videojuego;
import org.springframework.stereotype.Service;
import videojuegos.repository.VentaRepository;
import videojuegos.repository.VideojuegoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class VentaService {
    private final VentaRepository repo;
    private final VideojuegoRepository videojuegoRepo;

    public VentaService(VentaRepository repo, VideojuegoRepository videojuegoRepo) {
        this.repo = repo;
        this.videojuegoRepo = videojuegoRepo;
    }

    public List<Venta> findAll() { return repo.findAll(); }
    public Optional<Venta> findById(Long id) { return repo.findById(id); }

    public Venta save(Venta v) {
        Videojuego juego = videojuegoRepo.findById(v.getVideojuego().getId())
                .orElseThrow(() -> new RuntimeException("Videojuego no encontrado"));
        if (juego.getStock() < v.getCantidad())
            throw new RuntimeException("Stock insuficiente");

        juego.setStock(juego.getStock() - v.getCantidad());
        videojuegoRepo.save(juego);

        v.setTotal(v.getCantidad() * juego.getPrecio());
        return repo.save(v);
    }

    public void delete(Long id) { repo.deleteById(id); }
}
