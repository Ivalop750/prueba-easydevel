package prueba.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import prueba.models.Section;
import prueba.services.SectionService;

import java.util.Optional;

@RestController
@RequestMapping("/sections")
public class SectionController {

    @Autowired
    private SectionService sectionService;


    @PostMapping
    public ResponseEntity<?> addSection(@RequestBody Section section) {
        try {
            // Validación para el campo 'nombre'
            if (section.getNombre() == null || section.getNombre().isEmpty()) {
                return ResponseEntity.badRequest().body("El campo 'nombre' es obligatorio");
            }

            Section savedSection = sectionService.saveSection(section);
            return ResponseEntity.ok(savedSection);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al procesar la solicitud");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSection(@PathVariable Integer id) {
        Optional<Section> section = sectionService.getSectionById(id);
        if (section.isPresent()) {
            return ResponseEntity.ok(section.get());
        } else {
            String errorMessage = "La sección con id " + id + " no existe";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }
}
