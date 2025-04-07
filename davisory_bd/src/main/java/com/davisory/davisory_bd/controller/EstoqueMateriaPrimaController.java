package com.davisory.davisory_bd.controller;

import com.davisory.davisory_bd.dao.EstoqueMateriaPrimaRepositorio;
import com.davisory.davisory_bd.model.EstoqueMateriaPrima;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@Controller
public class EstoqueMateriaPrimaController {

    @GetMapping("/estoque_materia_prima")
    public String listar(Model model) {
        EstoqueMateriaPrimaRepositorio repo = new EstoqueMateriaPrimaRepositorio();
        List<EstoqueMateriaPrima> estoque = repo.listar();
        model.addAttribute("estoque", estoque);
        return "estoque_materia_prima";
    }
}