package videojuegos.controller;

import videojuegos.model.Venta;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import videojuegos.service.VentaService;

import java.util.List;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    private final VentaService service;
    public VentaController(VentaService service) { this.service = service; }

    @GetMapping
    public List<Venta> getAll() { return service.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Venta> getById(@PathVariable Long id) {
        return service.findById(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Venta v) {
        try {
            return ResponseEntity.ok(service.save(v));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Venta v) {
        return service.findById(id).map(existing -> {
            v.setId(existing.getId());
            try {
                return ResponseEntity.ok(service.save(v));
            } catch (RuntimeException e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
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
