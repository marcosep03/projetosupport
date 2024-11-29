package com.example.sistemadeportaria.model;

import com.example.sistemadeportaria.Enums.Marca;
import com.example.sistemadeportaria.Enums.Modelo;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


@Entity
public class Veiculos {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "nomeMotorista")
    @NotBlank(message = "O nome não ser vazio.")
    private String nome;

    @Column(name= "matricula")
    @NotNull(message = "Clique no Botão Gerar!")
    private String matricula;

    @Column(name= "turno")
    @NotBlank(message = "O turno não pode ser vazio.")
    @Size(min = 4, message = "No mínimo 4 caracteres.")
    private String turno;

    @Column(name = "placa")
    @NotBlank(message = "A placa não pode ser vazio.")
    private String placa;

    @Column(name = "modelo")
    @Enumerated(EnumType.STRING)
    @NotNull(message = "O campo não pode ser nulo.")
    private Modelo modelo;

    @Column(name = "marca")
    @Enumerated(EnumType.STRING)
    @NotNull(message = "O campo não pode ser nulo.")
    private Marca marca;


    public Integer getId() {

        return id;
    }

    public void setId(Integer id) {

        this.id = id;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {

        this.matricula = matricula;
    }

    public String getNome() {

        return nome;
    }

    public void setNome(String nome) {

        this.nome = nome;
    }

    public String getTurno() {

        return turno;
    }
    public void setTurno(String turno) {

        this.turno = turno;
    }

    public String getPlaca() {

        return placa;
    }

    public void setPlaca(String placa) {

        this.placa = placa;
    }

   public Modelo getModelo() {

        return modelo;
   }

   public void setModelo(Modelo modelo) {

        this.modelo = modelo;
   }

   public Marca getMarca() {

        return marca;
   }
   public void setMarca(Marca marca) {

        this.marca = marca;
   }

}
