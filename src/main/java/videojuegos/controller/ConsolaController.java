package videojuegos.controller;


import videojuegos.model.Consola;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import videojuegos.service.ConsolaService;

import java.util.List;

@RestController
@RequestMapping("/api/consolas")
public class ConsolaController {

    private final ConsolaService service;
    public ConsolaController(ConsolaService service) { this.service = service; }

    @GetMapping
    public List<Consola> getAll() { return service.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Consola> getById(@PathVariable Long id) {
        return service.findById(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Consola create(@RequestBody Consola c) { return service.save(c); }

    @PutMapping("/{id}")
    public ResponseEntity<Consola> update(@PathVariable Long id, @RequestBody Consola c) {
        return service.findById(id).map(existing -> {
            c.setId(existing.getId());
            return ResponseEntity.ok(service.save(c));
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
