package com.davisory.davisory_bd.controller;

import com.davisory.davisory_bd.dao.EnderecoRepositorio;
import com.davisory.davisory_bd.dao.FornecedorRepositorio;
import com.davisory.davisory_bd.model.Endereco;
import com.davisory.davisory_bd.model.Fornecedor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class FornecedorController {

    @GetMapping("/fornecedores")
    public String listar(Model model) {
        FornecedorRepositorio repo = new FornecedorRepositorio();
        List<Fornecedor> fornecedores = repo.listar();
        model.addAttribute("fornecedores", fornecedores);
        return "fornecedores";
    }

    @GetMapping("/fornecedor/editar/{cnpj}")
    public String editar(@PathVariable String cnpj, Model model) {
        FornecedorRepositorio fornecedorRepo = new FornecedorRepositorio();
        EnderecoRepositorio enderecoRepo = new EnderecoRepositorio();
        Fornecedor fornecedor = fornecedorRepo.buscarPorCnpj(cnpj);
        List<Endereco> enderecos = enderecoRepo.listar();
        model.addAttribute("fornecedor", fornecedor);
        model.addAttribute("enderecos", enderecos);
        return "fornecedor-editar";
    }

    @PostMapping("/fornecedor/salvar")
    public String salvar(@ModelAttribute Fornecedor fornecedor, Model model) {
        EnderecoRepositorio enderecoRepo = new EnderecoRepositorio();
        Map<String, String> erros = new HashMap<>();

        // Validação de telefone
        String telefone = fornecedor.getTelefoneFornecedor();
        String somenteNumeros = telefone.replaceAll("\\D", "");
        if (!somenteNumeros.matches("\\d{11}")) {
            erros.put("erroTelefone", "O número de telefone deve conter exatamente 11 dígitos.");
        } else {
            // Reaplica a formatação (opcional, se quiser exibir formatado)
            String formatado = String.format("(%s) %s-%s",
                    somenteNumeros.substring(0, 2),
                    somenteNumeros.substring(2, 7),
                    somenteNumeros.substring(7));
            fornecedor.setTelefoneFornecedor(formatado);
        }

        // Validação de email
        String email = fornecedor.getEmailFornecedor();
        if (email == null || !email.matches("^[\\w\\.-]+@[\\w\\.-]+\\.[a-z]{2,}$")) {
            erros.put("erroEmail", "O e-mail fornecido é inválido.");
        }

        if (!erros.isEmpty()) {
            model.addAttribute("fornecedor", fornecedor);
            model.addAttribute("enderecos", enderecoRepo.listar());
            model.addAllAttributes(erros);
            return "fornecedor-editar";
        }

        FornecedorRepositorio repo = new FornecedorRepositorio();
        repo.atualizar(fornecedor);
        return "redirect:/fornecedores";
    }

    @GetMapping("/fornecedor/novo")
    public String novo(Model model) {
        EnderecoRepositorio enderecoRepo = new EnderecoRepositorio();
        model.addAttribute("fornecedor", new Fornecedor());
        model.addAttribute("enderecos", enderecoRepo.listar());
        return "fornecedor-form";
    }

    @PostMapping("/fornecedor/salvar-novo")
    public String salvarNovo(@ModelAttribute Fornecedor fornecedor, Model model) {
        EnderecoRepositorio enderecoRepo = new EnderecoRepositorio();
        Map<String, String> erros = new HashMap<>();

        // Validação telefone
        String telefone = fornecedor.getTelefoneFornecedor();
        String somenteNumeros = telefone.replaceAll("\\D", "");
        if (!somenteNumeros.matches("\\d{11}")) {
            erros.put("erroTelefone", "O número de telefone deve conter exatamente 11 dígitos.");
        } else {
            String formatado = String.format("(%s) %s-%s",
                    somenteNumeros.substring(0, 2),
                    somenteNumeros.substring(2, 7),
                    somenteNumeros.substring(7));
            fornecedor.setTelefoneFornecedor(formatado);
        }

        // Validação e-mail
        String email = fornecedor.getEmailFornecedor();
        if (email == null || !email.matches("^[\\w\\.-]+@[\\w\\.-]+\\.[a-z]{2,}$")) {
            erros.put("erroEmail", "O e-mail fornecido é inválido.");
        }

        // Validação CNPJ (simples)
        String cnpj = fornecedor.getCnpjFornecedor();
        String somenteCnpjNumeros = cnpj.replaceAll("\\D", "");
        if (!somenteCnpjNumeros.matches("\\d{14}")) {
            erros.put("erroCnpj", "O CNPJ deve conter exatamente 14 números.");
        }

        if (!erros.isEmpty()) {
            model.addAttribute("fornecedor", fornecedor);
            model.addAttribute("enderecos", enderecoRepo.listar());
            model.addAllAttributes(erros);
            return "fornecedor-form";
        }

        FornecedorRepositorio repo = new FornecedorRepositorio();
        repo.inserir(fornecedor);
        return "redirect:/fornecedores";
    }

    @GetMapping("/fornecedor/grafico")
    public String graficoPorBairro(Model model) {
        FornecedorRepositorio fornecedorRepo = new FornecedorRepositorio();
        EnderecoRepositorio enderecoRepo = new EnderecoRepositorio();

        List<Fornecedor> fornecedores = fornecedorRepo.listar();
        Map<String, Integer> fornecedoresPorBairro = new HashMap<>();

        for (Fornecedor f : fornecedores) {
            Endereco endereco = enderecoRepo.buscarPorId(f.getFkEnderecoId());
            String bairro = endereco.getBairro();
            fornecedoresPorBairro.put(bairro, fornecedoresPorBairro.getOrDefault(bairro, 0) + 1);
        }

        model.addAttribute("fornecedoresPorBairro", fornecedoresPorBairro);
        return "grafico-fornecedores-bairro";
    }
}
