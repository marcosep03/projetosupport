package com.example.sistemadeportaria.dao;

import com.example.sistemadeportaria.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UsuarioDao extends JpaRepository<Usuario, Long> {

    @Query("select i from Usuario i where i.email = :email")
    public Usuario findByEmail(String email); // Busca um usuário pelo email

    @Query("select m from Usuario m where m.user = :user and m.senha = :senha")
    public Usuario buscarLogin(String user, String senha); // Busca um usuário pelo nome de usuário e senha
}
