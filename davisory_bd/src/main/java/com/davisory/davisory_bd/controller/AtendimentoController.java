package com.davisory.davisory_bd.controller;

import com.davisory.davisory_bd.dao.AtendimentoRepositorio;
import com.davisory.davisory_bd.dao.ClienteRepositorio;
import com.davisory.davisory_bd.dao.FuncionarioRepositorio;
import com.davisory.davisory_bd.dto.AtendimentoDTO;
import com.davisory.davisory_bd.model.Atendimento;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

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

        return "atendimentos-form";
    }

    @PostMapping("/atendimentos/salvar")
    public String salvarAtendimento(@ModelAttribute Atendimento atendimento) {
        atendimento.setDataAtendimento(Timestamp.valueOf(LocalDateTime.now()));
        atendimentoRepositorio.inserir(atendimento);
        return "redirect:/atendimentos";
    }
}