package com.davisory.davisory_bd.controller;

import com.davisory.davisory_bd.dao.ProdutoRepositorio;
import com.davisory.davisory_bd.model.Produto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class ProdutoController {

    ProdutoRepositorio produtoRepositorio = new ProdutoRepositorio();

    @GetMapping("/produtos")
    public String listarProdutos(Model model) {
        List<Produto> produtos = produtoRepositorio.listarProdutos();
        model.addAttribute("produtos", produtos);
        return "produtos";
    }

    @GetMapping("/produtos/editar/{id}")
    public String exibirFormularioEdicao(@PathVariable int id, Model model) {
        Produto produto = produtoRepositorio.buscarPorId(id);
        if (produto != null) {
            model.addAttribute("produto", produto);
            return "editarProduto";
        } else {
            return "redirect:/produtos";
        }
    }

    @PostMapping("/produtos/atualizar")
    public String atualizarProduto(
            @RequestParam int id,
            @RequestParam String nome,
            @RequestParam String descricao,
            @RequestParam double preco,
            @RequestParam("statusProduto") boolean ativo
    ) {
        Produto produto = new Produto();
        produto.setIdProduto(id);
        produto.setNomeProduto(nome);
        produto.setDescricaoProduto(descricao);
        produto.setPrecoProduto(preco);
        produto.setAtivo(ativo);
        produtoRepositorio.atualizarProduto(produto);
        return "redirect:/produtos";
    }

    @GetMapping("/produtos/adicionar")
    public String exibirFormularioAdicao(Model model) {
        model.addAttribute("produto", new Produto());
        return "adicionarProduto";
    }

    @PostMapping("/produtos/adicionar")
    public String adicionarProduto(
        @RequestParam String nomeProduto,
        @RequestParam String descricaoProduto,
        @RequestParam double precoProduto
    ) {
        Produto produto = new Produto();
        produto.setNomeProduto(nomeProduto);
        produto.setDescricaoProduto(descricaoProduto);
        produto.setPrecoProduto(precoProduto);
        produtoRepositorio.inserirProduto(produto);
        return "redirect:/produtos";
    }
    @GetMapping("/produtos/grafico")
    public String exibirGraficoPrecos(Model model) {
        List<Produto> produtos = produtoRepositorio.listarProdutos();
        model.addAttribute("produtos", produtos);
        return "graficoprecos";
    }
}