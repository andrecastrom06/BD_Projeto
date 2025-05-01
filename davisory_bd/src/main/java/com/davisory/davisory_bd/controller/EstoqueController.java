package com.davisory.davisory_bd.controller;

import com.davisory.davisory_bd.dao.EstoqueMateriaPrimaRepositorio;
import com.davisory.davisory_bd.dao.EstoqueProdutoRepositorio;
import com.davisory.davisory_bd.dao.MateriaPrimaRepositorio;
import com.davisory.davisory_bd.dao.ProdutoRepositorio;
import com.davisory.davisory_bd.dto.EstoqueMateriaPrimaDetalhadoDTO;
import com.davisory.davisory_bd.dto.EstoqueProdutoDetalhadoDTO;
import com.davisory.davisory_bd.model.EstoqueMateriaPrima;
import com.davisory.davisory_bd.model.EstoqueProduto;
import com.davisory.davisory_bd.model.MateriaPrima;
import com.davisory.davisory_bd.model.Produto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class EstoqueController {

    @GetMapping("/estoque")
    public String listar(Model model) {
        EstoqueMateriaPrimaRepositorio estoqueMateriaRepo = new EstoqueMateriaPrimaRepositorio();
        MateriaPrimaRepositorio materiaRepo = new MateriaPrimaRepositorio();
        List<EstoqueMateriaPrima> estoqueMateriaList = estoqueMateriaRepo.listar();
        List<EstoqueMateriaPrimaDetalhadoDTO> materiasDetalhadas = new ArrayList<>();
        for (EstoqueMateriaPrima e : estoqueMateriaList) {
            MateriaPrima m = materiaRepo.buscarPorId(e.getFkMateriaPrimaId());
            materiasDetalhadas.add(new EstoqueMateriaPrimaDetalhadoDTO(e, m));
        }
        EstoqueProdutoRepositorio estoqueProdutoRepo = new EstoqueProdutoRepositorio();
        ProdutoRepositorio produtoRepo = new ProdutoRepositorio();
        List<EstoqueProduto> estoqueProdutoList = estoqueProdutoRepo.listar();
        List<EstoqueProdutoDetalhadoDTO> produtosDetalhados = new ArrayList<>();
        for (EstoqueProduto e : estoqueProdutoList) {
            Produto p = produtoRepo.buscarPorId(e.getIdProduto());
            produtosDetalhados.add(new EstoqueProdutoDetalhadoDTO(e, p));
        }
        model.addAttribute("estoque", materiasDetalhadas);
        model.addAttribute("estoqueDetalhado", produtosDetalhados);
        return "estoque";
    }

    @GetMapping("/estoque/materia-prima/editar/{id}/{materiaId}")
    public String editarMateriaPrima(@PathVariable int id, @PathVariable int materiaId, Model model) {
        EstoqueMateriaPrimaRepositorio estoqueRepo = new EstoqueMateriaPrimaRepositorio();
        MateriaPrimaRepositorio materiaRepo = new MateriaPrimaRepositorio();
        EstoqueMateriaPrima estoque = estoqueRepo.buscarPorId(id, materiaId);
        MateriaPrima materia = materiaRepo.buscarPorId(materiaId);
        model.addAttribute("estoque", estoque);
        model.addAttribute("materia", materia);
        return "editarEstoqueMateriaPrima";
    }

    @GetMapping("/estoque/produto/editar/{id}/{produtoId}")
    public String editarProduto(@PathVariable int id, @PathVariable int produtoId, Model model) {
        EstoqueProdutoRepositorio estoqueRepo = new EstoqueProdutoRepositorio();
        ProdutoRepositorio produtoRepo = new ProdutoRepositorio();
        EstoqueProduto estoque = estoqueRepo.buscarPorId(id, produtoId);
        Produto produto = produtoRepo.buscarPorId(produtoId);
        model.addAttribute("estoque", estoque);
        model.addAttribute("produto", produto);
        return "editarEstoqueProduto";
    }

    @PostMapping("/estoque/materia-prima/atualizar")
    public String atualizarMateriaPrima(EstoqueMateriaPrima estoque) {
        EstoqueMateriaPrimaRepositorio repo = new EstoqueMateriaPrimaRepositorio();
        repo.atualizar(estoque);
        return "redirect:/estoque";
    }

    @PostMapping("/estoque/produto/atualizar")
    public String atualizarProduto(EstoqueProduto estoque) {
        EstoqueProdutoRepositorio repo = new EstoqueProdutoRepositorio();
        repo.atualizar(estoque);
        return "redirect:/estoque";
    }

    @GetMapping("/estoque/grafico")
    public String exibirGraficoQuantidadeMateriaPrima(Model model) {
        EstoqueMateriaPrimaRepositorio estoqueMateriaRepo = new EstoqueMateriaPrimaRepositorio();
        List<EstoqueMateriaPrima> estoqueMateriaList = estoqueMateriaRepo.listar();
        Map<Integer, Integer> quantidadeRepetida = new HashMap<>();
        for (EstoqueMateriaPrima estoque : estoqueMateriaList) {
            int quantidade = estoque.getQuantidadeMateriaPrima();
            quantidadeRepetida.put(quantidade, quantidadeRepetida.getOrDefault(quantidade, 0) + 1);
        }
        model.addAttribute("quantidadeRepetida", quantidadeRepetida);
        return "graficoQuantidadeMateriaPrima";
    }
}