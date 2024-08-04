package dev.jamersonaguiar.gestaovagas.exceptions;

public class CompanyFoundException extends RuntimeException {
    public CompanyFoundException() {
        super("Company already exists.");
    }
}
