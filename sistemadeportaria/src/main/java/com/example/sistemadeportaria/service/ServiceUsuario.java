package com.example.sistemadeportaria.service;

import com.example.sistemadeportaria.Exceptions.CriptoExistException;
import com.example.sistemadeportaria.Exceptions.EmailExistsException;
import com.example.sistemadeportaria.dao.UsuarioDao;
import com.example.sistemadeportaria.model.Usuario;
import com.example.sistemadeportaria.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
public class ServiceUsuario {

    @Autowired
    private UsuarioDao repositorioUsuario;

    public void salvarUsuario(Usuario user) throws Exception {

        try {
            // Verifica se já existe um usuário com o mesmo e-mail
            if(repositorioUsuario.findByEmail(user.getEmail()) != null){
                throw new EmailExistsException("Já existe um e-mail cadastrado para: " + user.getEmail());
            }
            // Criptografa a senha do usuário
            user.setSenha(Util.md5(user.getSenha()));


        } catch (NoSuchAlgorithmException e){
            // Lança exceção se houver erro na criptografia
            throw new CriptoExistException("Erro na criptografia da senha");
        }

        // Salva o usuário no banco de dados
        repositorioUsuario.save(user);

    }

    public Usuario loginUser(String user, String senha ) {

        // Busca o usuário no banco de dados
        Usuario userLogin = repositorioUsuario.buscarLogin(user, senha);
        return userLogin;
    }

}
