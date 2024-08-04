package dev.jamersonaguiar.gestaovagas.exceptions;

public class UserFoundException extends RuntimeException {
    public UserFoundException() {
        super("User already exists.");
    }
}
