package com.davisory.davisory_bd.controller;

import com.davisory.davisory_bd.dao.FuncionarioRepositorio;
import com.davisory.davisory_bd.model.Administrativo;
import com.davisory.davisory_bd.model.Funcionario;
import com.davisory.davisory_bd.model.Operacional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class FuncionarioController {

    FuncionarioRepositorio repositorio = new FuncionarioRepositorio();

    @GetMapping("/funcionarios")
    public String listarFuncionarios(Model model) {
        List<Administrativo> administrativos = repositorio.listarAdministrativos();
        List<Operacional> operacionais = repositorio.listarOperacionais();

        model.addAttribute("administrativos", administrativos);
        model.addAttribute("operacionais", operacionais);

        return "funcionarios";
    }
    @GetMapping("/funcionarios/editar/{id}")
    public String editarFuncionario(@PathVariable("id") int id, Model model) {
        Funcionario funcionario = repositorio.buscarPorId(id);
        model.addAttribute("funcionario", funcionario);
        return "editarFuncionario";
    }

    @PostMapping("/funcionarios/atualizar")
    public String atualizarFuncionario(@RequestParam("id") int id,
        @RequestParam("empregado") boolean empregado) {
        repositorio.atualizarEmpregado(id, empregado);
        return "redirect:/funcionarios";
    }

}
