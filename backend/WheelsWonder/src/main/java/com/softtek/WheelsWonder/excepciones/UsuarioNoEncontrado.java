package com.softtek.WheelsWonder.excepciones;

public class UsuarioNoEncontrado extends RuntimeException{
    public UsuarioNoEncontrado(String message) {
        super(message);
    }
}
