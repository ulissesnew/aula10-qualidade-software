package br.edu.qualidade;

/** Serviço mínimo usado para demonstrar as etapas de qualidade do pipeline. */
public final class GreetingService {
    public String greet(final String name) {
        if (name == null || name.isBlank()) {
            return "Olá, mundo!";
        }
        return "Olá, " + name.trim() + "!";
    }
}
