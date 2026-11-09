package br.edu.qualidade;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

/** Teste de integração isolado em contêiner, executado no job próprio do CI. */
@Testcontainers(disabledWithoutDocker = true)
class GreetingServiceIT {
    @Container
    static final GenericContainer<?> SERVICE = new GenericContainer<>("alpine:3.20")
            .withCommand("sh", "-c", "while true; do sleep 1; done");

    @Test
    void applicationServiceIsAvailable() {
        assertTrue(SERVICE.isRunning());
        assertTrue(new GreetingService().greet("integração").contains("integração"));
    }
}
