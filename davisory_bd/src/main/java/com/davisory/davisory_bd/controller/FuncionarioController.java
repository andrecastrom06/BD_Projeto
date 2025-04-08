package com.davisory.davisory_bd.controller;

import com.davisory.davisory_bd.dao.FuncionarioRepositorio;
import com.davisory.davisory_bd.model.Administrativo;
import com.davisory.davisory_bd.model.Funcionario;
import com.davisory.davisory_bd.model.Operacional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
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
    public String atualizarFuncionario(
            @RequestParam("id") int id,
            @RequestParam("nome") String nome,
            @RequestParam("salario") double salario,
            @RequestParam("dataContratacao") Date dataContratacao,
            @RequestParam(value = "chefe", required = false) Integer chefe,
            @RequestParam("empregado") boolean empregado) {

        Funcionario f = new Funcionario();
        f.setIdFuncionario(id);
        f.setNomeFuncionario(nome);
        f.setSalarioFuncionario(salario);
        f.setDataContratacaoFuncionario(dataContratacao);
        f.setChefeFuncionario(chefe);
        f.setEmpregado(empregado);

        repositorio.atualizarFuncionarioCompleto(f);
        return "redirect:/funcionarios";
    }

    @GetMapping("/chefes")
    public String listarFuncionariosComChefes(Model model) {
        List<Funcionario> funcionarios = repositorio.listarFuncionariosComChefes();
        model.addAttribute("funcionarios", funcionarios);
        return "chefes";
    }
}