package com.davisory.davisory_bd.controller;

import com.davisory.davisory_bd.dao.ProdutoRepositorio;
import com.davisory.davisory_bd.model.Produto;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ProdutoController {

    ProdutoRepositorio produtoRepositorio = new ProdutoRepositorio();

    @GetMapping("/produtos")
    public String exibirPaginaProdutos(Model model) {
        List<Produto> produtos = produtoRepositorio.listarProdutos();
        model.addAttribute("produtos", produtos);
        return "produtos";
    }
}
