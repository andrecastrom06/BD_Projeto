package com.davisory.davisory_bd.controller;

import com.davisory.davisory_bd.dao.EstoqueProdutoRepositorio;
import com.davisory.davisory_bd.model.EstoqueProduto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class EstoqueProdutoController {

    @GetMapping("/estoque_produtos")
    public String listar(Model model) {
        EstoqueProdutoRepositorio repo = new EstoqueProdutoRepositorio();
        List<EstoqueProduto> estoque = repo.listar();
        model.addAttribute("estoque", estoque);
        return "estoque_produtos";
    }
}
