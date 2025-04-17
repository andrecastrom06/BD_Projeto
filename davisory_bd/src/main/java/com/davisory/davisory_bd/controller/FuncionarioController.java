package com.davisory.davisory_bd.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.davisory.davisory_bd.dao.FuncionarioRepositorio;
import com.davisory.davisory_bd.dto.FuncionarioComChefeDTO;
import com.davisory.davisory_bd.model.Administrativo;
import com.davisory.davisory_bd.model.Funcionario;
import com.davisory.davisory_bd.model.Operacional;

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
        @RequestParam(value = "chefe", required = false) Integer chefe,
        @RequestParam("empregado") boolean empregado,
        @RequestParam("cargo") String cargo) {

        Funcionario f = new Funcionario();
        f.setIdFuncionario(id);
        f.setNomeFuncionario(nome);
        f.setSalarioFuncionario(salario);
        f.setChefeFuncionario(chefe);
        f.setEmpregado(empregado);
        f.setCargo(cargo);

        repositorio.atualizarFuncionarioCompleto(f);
        return "redirect:/funcionarios";
    }

    @GetMapping("/chefes")
    public String listarFuncionariosComChefes(Model model) {
        List<Funcionario> funcionarios = repositorio.listarFuncionariosComChefes();
        List<FuncionarioComChefeDTO> dtos = new ArrayList<>();

        for (Funcionario f : funcionarios) {
            String hierarquia = repositorio.obterHierarquiaChefe(f);
            dtos.add(new FuncionarioComChefeDTO(f.getNomeFuncionario(), hierarquia));
        }

        model.addAttribute("funcionarios", dtos);
        return "chefes";
    }

    @GetMapping("/funcionarios/adicionar")
    public String exibirFormularioAdicao(Model model) {
        List<Funcionario> chefes = repositorio.listarTodosFuncionarios();
        model.addAttribute("chefes", chefes);
        model.addAttribute("funcionario", new Funcionario());
        return "adicionarFuncionario";
    }

    @PostMapping("/funcionarios/adicionar")
    public String adicionarFuncionario(
        @RequestParam("nome") String nome,
        @RequestParam("salario") double salario,
        @RequestParam(value = "chefe", required = false) Integer chefe,
        @RequestParam("tipo") String tipoFuncionario,
        @RequestParam(value = "cargo", required = false) String cargo
    ) {
        Funcionario f;

        if ("Administrativo".equals(tipoFuncionario)) {
            Administrativo adm = new Administrativo();
            adm.setCargoFuncionarioAdministrativo(cargo);
            adm.setCargo(cargo);
            f = adm;
        } else {
            f = new Operacional();
        }

        f.setNomeFuncionario(nome);
        f.setSalarioFuncionario(salario);
        f.setChefeFuncionario(chefe);
        f.setEmpregado(true);

        int novoId = repositorio.inserir(f);

        if ("Administrativo".equals(tipoFuncionario)) {
            repositorio.inserirAdministrativo(novoId, cargo);
        } else {
            repositorio.inserirOperacional(novoId);
        }

        return "redirect:/funcionarios";
    }

    @GetMapping("/funcionarios/formulario")
    public String mostrarFormulario(Model model) {
        List<Funcionario> chefes = repositorio.listarTodosFuncionarios();
        model.addAttribute("chefes", chefes);
        model.addAttribute("funcionario", new Funcionario());
        return "formularioFuncionario";
    }

    @GetMapping("/funcionarios/adicionarChefe")
    public String exibirFormularioAtribuirChefe(Model model) {
        List<Funcionario> funcionarios = repositorio.listarTodosFuncionarios();
        model.addAttribute("funcionarios", funcionarios);
        return "adicionarChefe";
    }

    @PostMapping("/funcionarios/adicionarChefe")
    public String atribuirChefe(
        @RequestParam("funcionarioId") int funcionarioId,
        @RequestParam(value = "chefeId", required = false) Integer chefeId) {

        Funcionario funcionario = repositorio.buscarPorId(funcionarioId);
        if (funcionario != null) {
            funcionario.setChefeFuncionario(chefeId);
            repositorio.atualizarFuncionarioCompleto(funcionario);
        }

        return "redirect:/chefes";
    }
}