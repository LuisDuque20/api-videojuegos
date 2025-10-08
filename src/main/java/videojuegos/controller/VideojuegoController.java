package videojuegos.controller;

import videojuegos.model.Videojuego;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import videojuegos.service.VideojuegoService;

import java.util.List;

@RestController
@RequestMapping("/api/videojuegos")
public class VideojuegoController {

    private final VideojuegoService service;
    public VideojuegoController(VideojuegoService service) { this.service = service; }

    @GetMapping
    public List<Videojuego> getAll() { return service.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Videojuego> getById(@PathVariable Long id) {
        return service.findById(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Videojuego create(@RequestBody Videojuego v) { return service.save(v); }

    @PutMapping("/{id}")
    public ResponseEntity<Videojuego> update(@PathVariable Long id, @RequestBody Videojuego v) {
        return service.findById(id).map(existing -> {
            v.setId(existing.getId());
            return ResponseEntity.ok(service.save(v));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (service.findById(id).isPresent()) {
            service.delete(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
