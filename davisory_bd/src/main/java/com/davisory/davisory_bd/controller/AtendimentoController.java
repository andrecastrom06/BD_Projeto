package com.davisory.davisory_bd.controller;

import com.davisory.davisory_bd.dao.AtendimentoRepositorio;
import com.davisory.davisory_bd.model.Atendimento;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@Controller
public class AtendimentoController {

    @GetMapping("/atendimentos")
    public String listar(Model model) {
        AtendimentoRepositorio repo = new AtendimentoRepositorio();
        List<Atendimento> atendimentos = repo.listar();
        model.addAttribute("atendimentos", atendimentos);
        return "atendimentos";
    }
}