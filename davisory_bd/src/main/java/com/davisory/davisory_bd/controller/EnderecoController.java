package com.davisory.davisory_bd.controller;

import com.davisory.davisory_bd.dao.EnderecoRepositorio;
import com.davisory.davisory_bd.model.Endereco;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class EnderecoController {

    @GetMapping("/enderecos")
    public String listarEnderecos(Model model) {
        EnderecoRepositorio repo = new EnderecoRepositorio();
        List<Endereco> enderecos = repo.listar();
        model.addAttribute("enderecos", enderecos);
        return "enderecos";
    }

    @GetMapping("/enderecos/adicionar")
    public String mostrarFormularioAdicionar(Model model) {
        model.addAttribute("endereco", new Endereco());
        return "endereco-form";
    }

    @PostMapping("/enderecos/adicionar")
    public String adicionarEndereco(@ModelAttribute Endereco endereco) {
        EnderecoRepositorio repo = new EnderecoRepositorio();
        repo.inserir(endereco);
        return "redirect:/enderecos";
    }

    @GetMapping("/enderecos/editar/{id}")
    public String mostrarFormularioEdicao(@PathVariable int id, Model model) {
        EnderecoRepositorio repo = new EnderecoRepositorio();
        Endereco endereco = repo.buscarPorId(id);
        model.addAttribute("endereco", endereco);
        return "endereco-editar";
    }

    @PostMapping("/enderecos/editar")
    public String editarEndereco(@ModelAttribute Endereco endereco) {
        EnderecoRepositorio repo = new EnderecoRepositorio();
        repo.atualizar(endereco);
        return "redirect:/enderecos";
    }

    @GetMapping("/enderecos/{id:\\d+}")
    public String detalharEndereco(@PathVariable int id, Model model) {
        EnderecoRepositorio repo = new EnderecoRepositorio();
        Endereco endereco = repo.buscarPorId(id);
        model.addAttribute("endereco", endereco);
        return "endereco-detalhe";
    }

    @GetMapping("/enderecos/grafico")
    public String graficoBairros(Model model) {
        EnderecoRepositorio repo = new EnderecoRepositorio();
        List<Endereco> enderecos = repo.listar();

        Map<String, Long> contagemPorBairro = enderecos.stream()
            .collect(Collectors.groupingBy(
                Endereco::getBairro, Collectors.counting()
            ));

        model.addAttribute("bairros", contagemPorBairro);
        return "grafico-bairros";
    }
}