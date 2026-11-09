package br.edu.qualidade;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class GreetingServiceTest {
    private final GreetingService service = new GreetingService();

    @Test
    void greetsAProvidedName() {
        assertEquals("Olá, Ana!", service.greet(" Ana "));
    }

    @Test
    void usesTheDefaultGreetingForBlankNames() {
        assertEquals("Olá, mundo!", service.greet("  "));
        assertEquals("Olá, mundo!", service.greet(null));
    }
}
