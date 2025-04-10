package com.davisory.davisory_bd.controller;

import com.davisory.davisory_bd.dao.FuncionarioRepositorio;
import com.davisory.davisory_bd.dao.MontagemRepositorio;
import com.davisory.davisory_bd.dao.PedidoRepositorio;
import com.davisory.davisory_bd.dto.MontagemDTO;
import com.davisory.davisory_bd.model.Montagem;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MontagemController {

    @GetMapping("/montagens")
    public String listar(Model model) {
        MontagemRepositorio montagemRepo = new MontagemRepositorio();
        List<Montagem> montagens = montagemRepo.listar();

        FuncionarioRepositorio funcRepo = new FuncionarioRepositorio();
        PedidoRepositorio pedidoRepo = new PedidoRepositorio();

        List<MontagemDTO> detalhados = new ArrayList<>();

        for (Montagem m : montagens) {
            var funcionario = funcRepo.buscarPorId(m.getIdFuncionarioOperacional());
            var pedido = pedidoRepo.buscarPorId(m.getIdPedido());
            detalhados.add(new MontagemDTO(m, funcionario, pedido));
        }

        model.addAttribute("montagens", detalhados);
        return "montagens";
    }
}