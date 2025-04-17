package com.davisory.davisory_bd.controller;

import com.davisory.davisory_bd.dao.EnderecoRepositorio;
import com.davisory.davisory_bd.dao.FornecedorRepositorio;
import com.davisory.davisory_bd.model.Endereco;
import com.davisory.davisory_bd.model.Fornecedor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;

@Controller
public class FornecedorController {

    @GetMapping("/fornecedores")
    public String listar(Model model) {
        FornecedorRepositorio repo = new FornecedorRepositorio();
        List<Fornecedor> fornecedores = repo.listar();
        model.addAttribute("fornecedores", fornecedores);
        return "fornecedores";
    }

    @GetMapping("/fornecedor/editar/{cnpj}")
    public String editar(@PathVariable String cnpj, Model model) {
        FornecedorRepositorio fornecedorRepo = new FornecedorRepositorio();
        EnderecoRepositorio enderecoRepo = new EnderecoRepositorio();
        Fornecedor fornecedor = fornecedorRepo.buscarPorCnpj(cnpj);
        List<Endereco> enderecos = enderecoRepo.listar();
        model.addAttribute("fornecedor", fornecedor);
        model.addAttribute("enderecos", enderecos);
        return "fornecedor-editar";
    }

    @org.springframework.web.bind.annotation.PostMapping("/fornecedor/salvar")
    public String salvar(Fornecedor fornecedor) {
        FornecedorRepositorio repo = new FornecedorRepositorio();
        repo.atualizar(fornecedor);
        return "redirect:/fornecedores";
    }

    @GetMapping("/fornecedor/novo")
    public String novo(Model model) {
        EnderecoRepositorio enderecoRepo = new EnderecoRepositorio();
        model.addAttribute("fornecedor", new Fornecedor());
        model.addAttribute("enderecos", enderecoRepo.listar());
        return "fornecedor-form";
    }

    @PostMapping("/fornecedor/salvar-novo")
    public String salvarNovo(Fornecedor fornecedor) {
        FornecedorRepositorio repo = new FornecedorRepositorio();
        repo.inserir(fornecedor);
        return "redirect:/fornecedores";
    }
}