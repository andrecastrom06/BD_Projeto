package com.davisory.davisory_bd.controller;

import com.davisory.davisory_bd.dao.EstoqueMateriaPrimaRepositorio;
import com.davisory.davisory_bd.dao.MateriaPrimaRepositorio;
import com.davisory.davisory_bd.model.EstoqueMateriaPrima;
import com.davisory.davisory_bd.model.MateriaPrima;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
    @GetMapping("/materia_prima/adicionar")
    public String mostrarFormularioAdicionar(Model model) {
        model.addAttribute("materiaPrima", new MateriaPrima());
        return "formMateriaPrima";
    }
    @PostMapping("/materia_prima/adicionar")
    public String adicionarMateriaPrima(MateriaPrima materia) {
        MateriaPrimaRepositorio materiaRepo = new MateriaPrimaRepositorio();
        EstoqueMateriaPrimaRepositorio estoqueRepo = new EstoqueMateriaPrimaRepositorio();

        int novoId = materiaRepo.inserir(materia);
        if (novoId > 0) {
            EstoqueMateriaPrima estoque = new EstoqueMateriaPrima();
            estoque.setFkMateriaPrimaId(novoId);
            estoque.setQuantidadeMateriaPrima(0);
            estoqueRepo.inserir(estoque);
        }

        return "redirect:/materia_prima";
    }
    @GetMapping("/materia_prima/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable int id, Model model) {
        MateriaPrimaRepositorio repo = new MateriaPrimaRepositorio();
        MateriaPrima materia = repo.buscarPorId(id);
        model.addAttribute("materiaPrima", materia);
        return "formEditarMateriaPrima";
    }

    @PostMapping("/materia_prima/editar")
    public String editarMateriaPrima(MateriaPrima materia) {
        MateriaPrimaRepositorio repo = new MateriaPrimaRepositorio();
        repo.atualizar(materia);
        return "redirect:/materia_prima";
    }
}