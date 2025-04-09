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
import java.util.ArrayList;
import java.util.List;

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
}