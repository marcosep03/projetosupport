package com.example.sistemadeportaria.Exceptions;

public class CriptoExistException extends Exception {

    public CriptoExistException(String message) {
        super(message); // Chama o construtor da classe Exception com a mensagem recebida
    }

    private static final long serialVersionUID = 1L; // Identificador de versão da serialização
}
