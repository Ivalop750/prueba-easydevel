package prueba.services;

import prueba.models.Section;

import java.util.Optional;

public interface SectionService {
    Section saveSection(Section section);
    Optional<Section> getSectionById(Integer id);
}
