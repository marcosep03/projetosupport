package com.example.sistemadeportaria.dao;

import com.example.sistemadeportaria.model.Veiculos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VeiculoDao extends JpaRepository<Veiculos, Integer> {

    @Query("SELECT v FROM Veiculos v WHERE v.nome IS NOT NULL AND v.placa IS NOT NULL AND v.modelo IS NOT NULL AND v.marca IS NOT NULL AND v.matricula IS NOT NULL AND v.turno IS NOT NULL")
    List<Veiculos> findByVeiculosAtivos(); // Busca veiculos com todos os campos não nulos

    public List<Veiculos> findByNomeContainingIgnoreCase(String nome); // Busca veiculos pelo nome (sem considerar maiúsculas/minúsculas)
}
