package com.davisory.davisory_bd.controller;

import com.davisory.davisory_bd.dao.ClienteRepositorio;
import com.davisory.davisory_bd.model.Cliente;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}
