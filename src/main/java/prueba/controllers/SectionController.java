package prueba.controllers;


import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<Section> addSection(@RequestBody Section section) {
        Section savedSection = sectionService.saveSection(section);
        return ResponseEntity.ok(savedSection);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSection(@PathVariable Integer id) {
        Optional<Section> section = sectionService.getSectionById(id);
        if (section.isPresent()) {
            return ResponseEntity.ok(section.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
