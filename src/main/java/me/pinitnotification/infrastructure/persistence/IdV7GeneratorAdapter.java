package me.pinitnotification.infrastructure.persistence;

import me.pinitnotification.domain.shared.IdGenerator;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class IdV7GeneratorAdapter implements IdGenerator {
    @Override
    public UUID generate() {
        return UuidV7Generator.generate();
    }
}
