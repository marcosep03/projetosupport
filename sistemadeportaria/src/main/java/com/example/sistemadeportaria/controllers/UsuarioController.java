package com.example.sistemadeportaria.controllers;

import com.example.sistemadeportaria.Exceptions.ServiceExc;
import com.example.sistemadeportaria.dao.UsuarioDao;
import com.example.sistemadeportaria.model.Usuario;
import com.example.sistemadeportaria.model.Veiculos;
import com.example.sistemadeportaria.service.ServiceUsuario;
import com.example.sistemadeportaria.util.Util;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.NoSuchAlgorithmException;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioDao usuarioRepositorio;

    @Autowired
    private ServiceUsuario serviceUsuario;

    @GetMapping("/")
    public ModelAndView login() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("Login/login");
        mv.addObject("usuario", new Usuario());
        return mv;
    }

    @GetMapping("/index")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/home/index");
        mv.addObject("veiculo", new Veiculos());
        return mv;
    }

    @GetMapping("/cadastro")
    public ModelAndView cadastrar() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("usuario", new Usuario());
        mv.setViewName("Login/cadastro");
        return mv;
    }
    @PostMapping("salvarUsuario")
    public ModelAndView cadastrar(Usuario usuario) throws Exception {
        ModelAndView mv = new ModelAndView();
        serviceUsuario.salvarUsuario(usuario);
        mv.setViewName("redirect:/");
        return mv;

    }

    @PostMapping("/login")
    public ModelAndView login(@Valid Usuario usuario, BindingResult br, HttpSession session) throws NoSuchAlgorithmException {
        ModelAndView mv = new ModelAndView();

        // Verifica se há erros de validação
        if (br.hasErrors()) {
            mv.setViewName("Login/login");
            mv.addObject("usuario", usuario); // Retorna o objeto com os erros
            return mv;
        }

        // Tenta autenticar o usuário
        Usuario userLogin = serviceUsuario.loginUser(usuario.getUser(), Util.md5(usuario.getSenha()));

        // Verifica se o usuário foi encontrado
        if (userLogin == null) {
            mv.setViewName("Login/login");
            mv.addObject("msg", "Usuário ou senha inválidos.");
            mv.addObject("usuario", usuario);
            return mv;
        }

        // Caso contrário, redireciona para a página inicial
        session.setAttribute("usuarioLogado", userLogin);
        return index();
    }

    @PostMapping ("/logout")
    public ModelAndView logout(HttpSession session) {
        session.invalidate();
        return login();
    }

}
