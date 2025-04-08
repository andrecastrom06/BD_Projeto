package com.davisory.davisory_bd.controller;

import com.davisory.davisory_bd.dao.EstoqueMateriaPrimaRepositorio;
import com.davisory.davisory_bd.dao.MateriaPrimaRepositorio;
import com.davisory.davisory_bd.dto.EstoqueMateriaPrimaDetalhadoDTO;
import com.davisory.davisory_bd.model.EstoqueMateriaPrima;
import com.davisory.davisory_bd.model.MateriaPrima;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.ArrayList;
import java.util.List;

@Controller
public class EstoqueMateriaPrimaController {

    @GetMapping("/estoque_materia_prima")
    public String listar(Model model) {
        EstoqueMateriaPrimaRepositorio estoqueRepo = new EstoqueMateriaPrimaRepositorio();
        MateriaPrimaRepositorio materiaRepo = new MateriaPrimaRepositorio();

        List<EstoqueMateriaPrima> estoqueList = estoqueRepo.listar();
        List<EstoqueMateriaPrimaDetalhadoDTO> detalhados = new ArrayList<>();

        for (EstoqueMateriaPrima e : estoqueList) {
            MateriaPrima m = materiaRepo.buscarPorId(e.getFkMateriaPrimaId());
            detalhados.add(new EstoqueMateriaPrimaDetalhadoDTO(e, m));
        }        

        model.addAttribute("estoque", detalhados);
        return "estoque_materia_prima";
    }
}