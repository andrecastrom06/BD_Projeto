package com.davisory.davisory_bd.controller;

import com.davisory.davisory_bd.dao.FornecedorRepositorio;
import com.davisory.davisory_bd.dao.FuncionarioRepositorio;
import com.davisory.davisory_bd.dao.MateriaPrimaRepositorio;
import com.davisory.davisory_bd.dao.SolicitacaoRepositorio;
import com.davisory.davisory_bd.dto.SolicitacaoDTO;
import com.davisory.davisory_bd.model.Funcionario;
import com.davisory.davisory_bd.model.Solicitacao;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        @RequestParam String cnpjFornecedor,
        @RequestParam int idMateriaPrima,
        HttpSession session
    ) {
        Funcionario funcionarioLogado = (Funcionario) session.getAttribute("usuarioLogado");
        if (funcionarioLogado == null) {
            return "redirect:/"; // se não estiver logado
        }

        Solicitacao solicitacao = new Solicitacao();
        solicitacao.setCnpjFornecedor(cnpjFornecedor);
        solicitacao.setIdMateriaPrima(idMateriaPrima);
        solicitacao.setIdFuncionario(funcionarioLogado.getIdFuncionario());
        solicitacao.setDataSolicitacao(LocalDateTime.now());

        new SolicitacaoRepositorio().inserir(solicitacao);
        return "redirect:/solicitacoes";
    }
    
    @GetMapping("/solicitacoes/adicionar")
    public String handleGetInvalido() {
        return "redirect:/solicitacoes/nova";
    }

    @GetMapping("/solicitacoes/editar")
    public String editarSolicitacaoForm(
        @RequestParam("cnpj") String cnpjFornecedor,
        @RequestParam("idMateria") int idMateriaPrima,
        @RequestParam("idFunc") int idFuncionario,
        Model model
    ) {
        Solicitacao solicitacao = new SolicitacaoRepositorio().buscarPorId(cnpjFornecedor, idMateriaPrima, idFuncionario);
        model.addAttribute("solicitacao", solicitacao);
        model.addAttribute("fornecedores", new FornecedorRepositorio().listar());
        model.addAttribute("materiasPrimas", new MateriaPrimaRepositorio().listar());
        model.addAttribute("administrativos", new FuncionarioRepositorio().listarAdministrativos());
        return "editarSolicitacao";
    }

    @PostMapping("/solicitacoes/editar")
    public String salvarEdicaoSolicitacao(
        @RequestParam String cnpjAntigo,
        @RequestParam int idMateriaAntiga,
        @RequestParam int idFuncAntigo,

        @RequestParam String cnpjFornecedor,
        @RequestParam int idMateriaPrima,
        @RequestParam int idFuncionario
    ) {
        Solicitacao solicitacao = new SolicitacaoRepositorio().buscarPorId(cnpjAntigo, idMateriaAntiga, idFuncAntigo);
        solicitacao.setCnpjFornecedor(cnpjFornecedor);
        solicitacao.setIdMateriaPrima(idMateriaPrima);
        solicitacao.setIdFuncionario(idFuncionario);
        new SolicitacaoRepositorio().atualizar(solicitacao, cnpjAntigo, idMateriaAntiga, idFuncAntigo);
        return "redirect:/solicitacoes";
    }

    @GetMapping("/solicitacoes/deletar/{cnpj}/{idMateria}/{idFuncionario}")
    public String deletarSolicitacao(@PathVariable String cnpj,
                                    @PathVariable int idMateria,
                                    @PathVariable int idFuncionario) {
        new SolicitacaoRepositorio().deletar(cnpj, idMateria, idFuncionario);
        return "redirect:/solicitacoes";
    }

    @GetMapping("/solicitacoes/grafico")
    public String graficoSolicitacoesPorFuncionario(Model model) {
        SolicitacaoRepositorio repo = new SolicitacaoRepositorio();
        List<SolicitacaoDTO> solicitacoes = repo.listarSolicitacoesComNomes();

        Map<String, Integer> contagemPorFuncionario = new HashMap<>();

        for (SolicitacaoDTO s : solicitacoes) {
            String nome = s.getNomeFuncionarioAdministrativo();
            contagemPorFuncionario.put(nome, contagemPorFuncionario.getOrDefault(nome, 0) + 1);
        }

        model.addAttribute("contagemFuncionarios", contagemPorFuncionario);
        return "grafico-solicitacoes-funcionario";
    }
}