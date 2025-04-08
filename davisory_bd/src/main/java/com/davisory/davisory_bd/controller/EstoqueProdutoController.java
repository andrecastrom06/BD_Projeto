package com.davisory.davisory_bd.controller;

import com.davisory.davisory_bd.dao.EstoqueProdutoRepositorio;
import com.davisory.davisory_bd.dao.ProdutoRepositorio;
import com.davisory.davisory_bd.dto.EstoqueProdutoDetalhadoDTO;
import com.davisory.davisory_bd.model.EstoqueProduto;
import com.davisory.davisory_bd.model.Produto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class EstoqueProdutoController {

    @GetMapping("/estoque_produtos")
    public String listar(Model model) {
        EstoqueProdutoRepositorio estoqueRepo = new EstoqueProdutoRepositorio();
        ProdutoRepositorio produtoRepo = new ProdutoRepositorio();

        List<EstoqueProduto> estoqueList = estoqueRepo.listar();
        List<EstoqueProdutoDetalhadoDTO> detalhados = new ArrayList<>();

        for (EstoqueProduto e : estoqueList) {
            Produto p = produtoRepo.buscarPorId(e.getIdProduto());
            detalhados.add(new EstoqueProdutoDetalhadoDTO(e, p));
        }

        model.addAttribute("estoqueDetalhado", detalhados);
        return "estoque_produtos";
    }
}