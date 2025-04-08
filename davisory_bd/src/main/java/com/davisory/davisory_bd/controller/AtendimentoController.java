package com.davisory.davisory_bd.controller;

import com.davisory.davisory_bd.dao.AtendimentoRepositorio;
import com.davisory.davisory_bd.dto.AtendimentoDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AtendimentoController {

    private final AtendimentoRepositorio atendimentoRepositorio = new AtendimentoRepositorio();

    @GetMapping("/atendimentos")
    public String listarAtendimentos(Model model) {
        List<AtendimentoDTO> atendimentos = atendimentoRepositorio.listarAtendimentosComFuncionario();
        model.addAttribute("atendimentos", atendimentos);
        return "atendimentos";
    }
}