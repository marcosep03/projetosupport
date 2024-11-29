package com.example.sistemadeportaria.Enums;

public enum Modelo {

    CARRO("Carro"),
    VAN("Van"),
    ONIBUS("Ônibus"),
    MOTO("Moto"),
    CAMINHAO("Caminhão");

    private String modelo;

    private Modelo(String modelo){
        this.modelo = modelo;
    }

    public String getModelo() {
        return modelo;
    }
}