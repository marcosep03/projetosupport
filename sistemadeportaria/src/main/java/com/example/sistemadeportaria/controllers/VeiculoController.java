package com.example.sistemadeportaria.controllers;

import com.example.sistemadeportaria.dao.VeiculoDao;
import com.example.sistemadeportaria.model.Veiculos;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@Controller
public class VeiculoController {

    @Autowired
    private VeiculoDao veiculorepositorio;

    @GetMapping("/inserirVeiculos")
    public ModelAndView insertVeiculos(Veiculos veiculo) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("veiculo/formVeiculos"); // Caminho para o template
        mv.addObject("veiculo", veiculo); // Cria um novo objeto Veiculo
        return mv;
    }

    @PostMapping("insertVeiculos")
    public ModelAndView inserirVeiculo(@Valid Veiculos veiculo, BindingResult br) {
        ModelAndView mv = new ModelAndView();

        // Verifica se há erros de validação
        if(br.hasErrors()) {
            mv.setViewName("veiculo/formVeiculos");
            mv.addObject("veiculo", veiculo); // Retorna o objeto com os erros
        }else {
            mv.setViewName("redirect:/veiculos-adicionados");
            veiculorepositorio.save(veiculo); // Salva o veículo
        }
        return mv;
    }

    @GetMapping("/veiculos-adicionados")
    public ModelAndView listagemVeiculos() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/veiculo/listVeiculos");
        mv.addObject("veiculosList", veiculorepositorio.findAll()); // Exibe todos os veículos
        return mv;
    }

    @GetMapping("/alterar/{id}")
    public ModelAndView alterar(@PathVariable("id") Integer id) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("veiculo/alterar");
        Veiculos veiculo = veiculorepositorio.getOne(id); // Obtém o veículo a ser alterado
        mv.addObject("veiculo", veiculo); // Retorna o veículo para edição
        return mv;
    }

    @PostMapping("/alterar")
    public ModelAndView alterar(Veiculos veiculo) {
        ModelAndView mv = new ModelAndView();
        veiculorepositorio.save(veiculo); // Atualiza o veículo no banco
        mv.setViewName("redirect:/veiculos-adicionados"); // Redireciona para a lista de veículos
        return mv;
    }

    @GetMapping("/excluir/{id}")
    public String excluirVeículo(@PathVariable("id") Integer id) {
        veiculorepositorio.deleteById(id); // Exclui o veículo pelo id
        return "redirect:/veiculos-adicionados"; // Redireciona para a lista de veículos
    }

    @GetMapping("filtro-veiculos")
    public ModelAndView filtroVeiculos() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/veiculo/filtroVeiculos"); // Exibe a tela de filtro de veículos
        return mv;
    }

    @GetMapping("/veiculos-ativos")
    public ModelAndView listagemVeiculosAtivos() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/veiculo/veiculos-ativos");
        mv.addObject("veiculosAtivos", veiculorepositorio.findByVeiculosAtivos()); // Exibe os veículos ativos
        return mv;
    }

    @PostMapping("pesquisar-veiculo")
    public ModelAndView pesquisarVeiculo(@RequestParam(required = false) String nome){
        ModelAndView mv = new ModelAndView();
        List<Veiculos> listaVeiculos;

        // Verifica se o nome foi informado e realiza a pesquisa
        if (nome == null || nome.trim().isEmpty()) {
            listaVeiculos = veiculorepositorio.findAll(); // Busca todos os veículos
        }else{
            listaVeiculos = veiculorepositorio.findByNomeContainingIgnoreCase(nome); // Busca por nome
        }

        mv.addObject("ListaDeVeiculos", listaVeiculos); // Adiciona a lista de veículos na view
        mv.setViewName("/veiculo/pesquisa-resultado"); // Exibe os resultados da pesquisa
        return mv;
    }
}
