package prueba.services;

import prueba.models.Section;
import prueba.repositories.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class SectionServiceImpl implements SectionService {

    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private static final String REDIS_PREFIX = "section_";

    @Override
    public Section saveSection(Section section) {
        return sectionRepository.save(section);
    }

    @Override
    public Optional<Section> getSectionById(Integer id) {
        String redisKey = REDIS_PREFIX + id;
        Section section = (Section) redisTemplate.opsForValue().get(redisKey);

        if (section != null) {
            return Optional.of(section);
        } else {
            Optional<Section> sectionFromDb = sectionRepository.findById(id);
            sectionFromDb.ifPresent(sec -> redisTemplate.opsForValue().set(redisKey, sec, 10, TimeUnit.MINUTES));
            return sectionFromDb;
        }
    }
}
