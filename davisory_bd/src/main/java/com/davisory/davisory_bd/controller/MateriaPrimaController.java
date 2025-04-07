package com.davisory.davisory_bd.controller;

import com.davisory.davisory_bd.dao.MateriaPrimaRepositorio;
import com.davisory.davisory_bd.model.MateriaPrima;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@Controller
public class MateriaPrimaController {

    @GetMapping("/materia_prima")
    public String listar(Model model) {
        MateriaPrimaRepositorio repo = new MateriaPrimaRepositorio();
        List<MateriaPrima> materias = repo.listar();
        model.addAttribute("materias", materias);
        return "materia_prima";
    }
}