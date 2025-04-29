package com.davisory.davisory_bd.controller;

import com.davisory.davisory_bd.dao.ClienteRepositorio;
import com.davisory.davisory_bd.dao.EnderecoRepositorio;
import com.davisory.davisory_bd.model.Cliente;
import com.davisory.davisory_bd.model.Endereco;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String editarCliente(@PathVariable String cpfCnpj, Model model) {
        if (!model.containsAttribute("cliente")) {
            Cliente cliente = clienteRepositorio.buscarPorCpfCnpj(cpfCnpj);
            model.addAttribute("cliente", cliente);
        }
        return "editarCliente";
    }

    @PostMapping("/clientes/atualizar")
    public String atualizarCliente(
        @RequestParam String cpfCnpj,
        @RequestParam String nome,
        @RequestParam String telefone,
        @RequestParam String email,
        RedirectAttributes redirectAttributes
    ) {
        Cliente cliente = new Cliente();
        try {
            cliente.setCpfCnpjCliente(cpfCnpj); 
            cliente.setNomeCliente(nome);
            cliente.setTelefoneCliente(telefone); 
            cliente.setEmailCliente(email); 
            clienteRepositorio.atualizar(cliente);

            return "redirect:/clientes";  
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("erro", e.getMessage());
            cliente.setCpfCnpjCliente(cpfCnpj);
            cliente.setNomeCliente(nome);
            cliente.setEmailCliente(email);
            redirectAttributes.addFlashAttribute("cliente", cliente);
            return "redirect:/clientes/editar/" + cpfCnpj;
        }
    }
    
    @PostMapping("/clientes/adicionar")
    public String adicionarCliente(
        @RequestParam String cpfCnpj,
        @RequestParam String nome,
        @RequestParam String telefone,
        @RequestParam String email,
        @RequestParam int idEndereco,
        Model model
    ) { 
        try {
            Cliente cliente = new Cliente();
            cliente.setCpfCnpjCliente(cpfCnpj);  
            cliente.setNomeCliente(nome);
            cliente.setTelefoneCliente(telefone);
            cliente.setEmailCliente(email);
            
            EnderecoRepositorio enderecoRepositorio = new EnderecoRepositorio();
            Endereco endereco = enderecoRepositorio.buscarPorId(idEndereco);
            cliente.setEndereco(endereco);
            
            clienteRepositorio.inserir(cliente);
            return "redirect:/clientes";
        } catch (IllegalArgumentException e) {
            model.addAttribute("erro", e.getMessage());
            
            EnderecoRepositorio enderecoRepositorio = new EnderecoRepositorio();
            model.addAttribute("enderecos", enderecoRepositorio.listar());
            
            return "adicionarcliente";
        }
    }

    @GetMapping("/clientes/adicionar")
    public String exibirFormularioAdicao(Model model) {
        EnderecoRepositorio enderecoRepositorio = new EnderecoRepositorio();
        model.addAttribute("cliente", new Cliente());
        model.addAttribute("enderecos", enderecoRepositorio.listar());
        return "adicionarcliente";
    }
}