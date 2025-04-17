package com.davisory.davisory_bd.controller;

import com.davisory.davisory_bd.dao.EnderecoRepositorio;
import com.davisory.davisory_bd.model.Endereco;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@Controller
public class EnderecoController {
    @GetMapping("/enderecos")
    public String listarEnderecos(Model model) {
        EnderecoRepositorio repo = new EnderecoRepositorio();
        List<Endereco> enderecos = repo.listar();
        model.addAttribute("enderecos", enderecos);
        return "enderecos";
    }

    @GetMapping("/enderecos/{id}")
    public String detalharEndereco(@PathVariable int id, Model model) {
        EnderecoRepositorio repo = new EnderecoRepositorio();
        Endereco endereco = repo.buscarPorId(id);
        model.addAttribute("endereco", endereco);
        return "endereco-detalhe";
    }
}