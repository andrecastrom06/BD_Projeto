package com.davisory.davisory_bd.controller;

import com.davisory.davisory_bd.dao.FornecedorRepositorio;
import com.davisory.davisory_bd.model.Fornecedor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
}