package com.davisory.davisory_bd.controller;

import com.davisory.davisory_bd.dao.FornecedorRepositorio;
import com.davisory.davisory_bd.dao.FuncionarioRepositorio;
import com.davisory.davisory_bd.dao.MateriaPrimaRepositorio;
import com.davisory.davisory_bd.dao.SolicitacaoRepositorio;
import com.davisory.davisory_bd.dto.SolicitacaoDTO;
import com.davisory.davisory_bd.model.Solicitacao;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class SolicitacaoController {

    @GetMapping("/solicitacoes")
    public String listarSolicitacoes(Model model) {
        SolicitacaoRepositorio repo = new SolicitacaoRepositorio();
        List<SolicitacaoDTO> solicitacoes = repo.listarSolicitacoesComNomes();
        model.addAttribute("solicitacoes", solicitacoes);
        return "solicitacoes";
    }
    @GetMapping("/solicitacoes/nova")
    public String novaSolicitacaoForm(Model model) {
        FornecedorRepositorio fornecedorRepo = new FornecedorRepositorio();
        MateriaPrimaRepositorio materiaRepo = new MateriaPrimaRepositorio();
        FuncionarioRepositorio funcionarioRepo = new FuncionarioRepositorio();

        model.addAttribute("fornecedores", fornecedorRepo.listar());
        model.addAttribute("materiasPrimas", materiaRepo.listar());
        model.addAttribute("administrativos", funcionarioRepo.listarAdministrativos());

        return "adicionarSolicitacao";
    }

    @PostMapping("/solicitacoes/adicionar")
    public String adicionarSolicitacao(
        @RequestParam("cnpjFornecedor") String cnpjFornecedor,
        @RequestParam("idMateriaPrima") int idMateriaPrima,
        @RequestParam("idFuncionario") int idFuncionario
    ) {
        Solicitacao solicitacao = new Solicitacao();
        solicitacao.setCnpjFornecedor(cnpjFornecedor);
        solicitacao.setIdMateriaPrima(idMateriaPrima);
        solicitacao.setIdFuncionario(idFuncionario);
        solicitacao.setDataSolicitacao(LocalDateTime.now());

        SolicitacaoRepositorio solicitacaoRepo = new SolicitacaoRepositorio();
        solicitacaoRepo.inserir(solicitacao);

        return "redirect:/solicitacoes";
    }
    @GetMapping("/solicitacoes/adicionar")
    public String handleGetInvalido() {
        return "redirect:/solicitacoes/nova"; // Ou alguma view de erro amigável
    }
}