package com.davisory.davisory_bd.controller;

import com.davisory.davisory_bd.dao.ClienteRepositorio;
import com.davisory.davisory_bd.model.Cliente;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ClienteController {

    ClienteRepositorio clienteRepositorio = new ClienteRepositorio();

    @GetMapping("/clientes")
    public String exibirPaginaClientes(Model model) {
        List<Cliente> clientes = clienteRepositorio.listarTodos();
        model.addAttribute("clientes", clientes);
        return "clientes";
    }
    @GetMapping("/clientes/editar/{cpfCnpj}")
    public String editarCliente(@PathVariable("cpfCnpj") String cpfCnpj, Model model) {
        Cliente cliente = clienteRepositorio.buscarPorCpfCnpj(cpfCnpj);
        model.addAttribute("cliente", cliente);
        return "editarCliente";
    }

    @PostMapping("/clientes/atualizar")
    public String atualizarCliente(
        @RequestParam("cpfCnpj") String cpfCnpj,
        @RequestParam("nome") String nome,
        @RequestParam("telefone") String telefone,
        @RequestParam("email") String email
    ) {
        Cliente cliente = new Cliente();
        cliente.setCpfCnpjCliente(cpfCnpj);
        cliente.setNomeCliente(nome);
        cliente.setTelefoneCliente(telefone);
        cliente.setEmailCliente(email);

        clienteRepositorio.atualizar(cliente);
        return "redirect:/clientes";
    }
    @GetMapping("/clientes/adicionar")
    public String exibirFormularioAdicao(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "adicionarcliente";
    }

    @PostMapping("/clientes/adicionar")
    public String adicionarCliente(
        @RequestParam("cpfCnpj") String cpfCnpj,
        @RequestParam("nome") String nome,
        @RequestParam("telefone") String telefone,
        @RequestParam("email") String email
    ) {
        Cliente cliente = new Cliente();
        cliente.setCpfCnpjCliente(cpfCnpj);
        cliente.setNomeCliente(nome);
        cliente.setTelefoneCliente(telefone);
        cliente.setEmailCliente(email);

        clienteRepositorio.inserir(cliente);
        return "redirect:/clientes";
    }
}

