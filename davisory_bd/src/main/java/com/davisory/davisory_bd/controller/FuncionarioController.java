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
    public String editarFuncionario(@PathVariable int id, Model model) {
        Funcionario funcionario = repositorio.buscarPorId(id);
        model.addAttribute("funcionario", funcionario);
        return "editarFuncionario";
    }

    @PostMapping("/funcionarios/atualizar")
    public String atualizarFuncionario(
        @RequestParam int id,
        @RequestParam String nome,
        @RequestParam double salario,
        @RequestParam(required = false) Integer chefe,
        @RequestParam boolean empregado,
        @RequestParam String cargo) {
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

    @GetMapping("/funcionarios/adicionar")
    public String mostrarFormularioAdicionar(Model model) {
        model.addAttribute("funcionario", new Funcionario());
        return "adicionarFuncionario";
    }

    @GetMapping("/chefes")
    public String listarFuncionariosComChefes(Model model) {
        List<Funcionario> funcionarios = repositorio.listarFuncionariosComChefes();
        List<FuncionarioComChefeDTO> dtos = new ArrayList<>();
        for (Funcionario f : funcionarios) {
            String hierarquia = repositorio.obterHierarquiaChefe(f);
            dtos.add(new FuncionarioComChefeDTO(f.getIdFuncionario(), f.getNomeFuncionario(), hierarquia));
        }
        model.addAttribute("funcionarios", dtos);
        return "chefes";
    }

    @GetMapping("/funcionarios/editarChefe/{id}")
    public String exibirFormularioEditarChefe(@PathVariable int id, Model model) {
        Funcionario funcionario = repositorio.buscarPorId(id);
        List<Funcionario> todosFuncionarios = repositorio.listarTodosFuncionarios();
        model.addAttribute("funcionario", funcionario);
        model.addAttribute("funcionarios", todosFuncionarios);
        return "editarChefe";
    }

    @PostMapping("/funcionarios/editarChefe")
    public String atualizarChefe(
        @RequestParam int funcionarioId,
        @RequestParam(required = false) Integer chefeId) {
        Funcionario funcionario = repositorio.buscarPorId(funcionarioId);
        if (funcionario != null) {
            funcionario.setChefeFuncionario(chefeId);
            repositorio.atualizarFuncionarioCompleto(funcionario);
        }
        return "redirect:/chefes";
    }
}