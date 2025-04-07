package com.davisory.davisory_bd.controller;

import com.davisory.davisory_bd.dao.SolicitacaoRepositorio;
import com.davisory.davisory_bd.model.Solicitacao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@Controller
public class SolicitacaoController {

    @GetMapping("/solicitacoes")
    public String listar(Model model) {
        SolicitacaoRepositorio repo = new SolicitacaoRepositorio();
        List<Solicitacao> solicitacoes = repo.listar();
        model.addAttribute("solicitacoes", solicitacoes);
        return "solicitacoes";
    }
}