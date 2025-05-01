package com.davisory.davisory_bd.controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.davisory.davisory_bd.dao.AtendimentoRepositorio;
import com.davisory.davisory_bd.dao.ClienteRepositorio;
import com.davisory.davisory_bd.dao.FuncionarioRepositorio;
import com.davisory.davisory_bd.dto.AtendimentoDTO;
import com.davisory.davisory_bd.model.Atendimento;

@Controller
public class AtendimentoController {
    private final AtendimentoRepositorio atendimentoRepositorio = new AtendimentoRepositorio();
    private final ClienteRepositorio clienteRepositorio = new ClienteRepositorio();
    private final FuncionarioRepositorio funcionarioRepositorio = new FuncionarioRepositorio();

    @GetMapping("/atendimentos")
    public String listarAtendimentos(Model model) {
        List<AtendimentoDTO> atendimentos = atendimentoRepositorio.listarAtendimentosComFuncionario();
        model.addAttribute("atendimentos", atendimentos);
        return "atendimentos";
    }

    @GetMapping("/atendimentos/novo")
    public String novoAtendimentoForm(Model model) {
        Atendimento atendimento = new Atendimento();
        atendimento.setDataAtendimento(Timestamp.valueOf(LocalDateTime.now()));
        model.addAttribute("atendimento", atendimento);
        model.addAttribute("clientes", clienteRepositorio.listarTodos());
        model.addAttribute("funcionarios", funcionarioRepositorio.listarAdministrativos());
        return "adicionaratendimento";
    }

    @PostMapping("/atendimentos/salvar")
    public String salvarAtendimento(@ModelAttribute Atendimento atendimento) {
        atendimento.setDataAtendimento(Timestamp.valueOf(LocalDateTime.now()));
        atendimentoRepositorio.inserir(atendimento);
        return "redirect:/atendimentos";
    }

    @GetMapping("/atendimentos/deletar/{cpf}/{idFuncionario}")
    public String deletarAtendimento(@PathVariable String cpf,
                                    @PathVariable int idFuncionario) {
        atendimentoRepositorio.deletar(cpf, idFuncionario);
        return "redirect:/atendimentos";
    }

    @GetMapping("/atendimentos/grafico")
    public String graficoAtendimentosPorFuncionario(Model model) {
        List<AtendimentoDTO> atendimentos = atendimentoRepositorio.listarAtendimentosComFuncionario();

        Map<String, Integer> contagemPorFuncionario = new HashMap<>();

        for (AtendimentoDTO a : atendimentos) {
            String nomeFuncionario = a.getNomeFuncionario();
            contagemPorFuncionario.put(nomeFuncionario, contagemPorFuncionario.getOrDefault(nomeFuncionario, 0) + 1);
        }

        model.addAttribute("contagemFuncionarios", contagemPorFuncionario);
        return "grafico-atendimentos-funcionario";
    }
}