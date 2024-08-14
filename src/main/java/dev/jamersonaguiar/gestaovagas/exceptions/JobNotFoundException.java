package dev.jamersonaguiar.gestaovagas.exceptions;

public class JobNotFoundException extends RuntimeException {
    public JobNotFoundException() {
        super("Job not found.");
    }
}
