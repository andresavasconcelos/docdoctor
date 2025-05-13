package br.com.docdoctor.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id) {
        super("Usuário de id " + id + " não encontrado");
    }
}
