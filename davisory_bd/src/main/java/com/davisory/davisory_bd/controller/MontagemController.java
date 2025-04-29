package com.davisory.davisory_bd.controller;

import com.davisory.davisory_bd.dao.FuncionarioRepositorio;
import com.davisory.davisory_bd.dao.MontagemRepositorio;
import com.davisory.davisory_bd.dao.PedidoRepositorio;
import com.davisory.davisory_bd.dto.MontagemDTO;
import com.davisory.davisory_bd.model.Montagem;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/montagens/adicionar")
    public String mostrarFormularioAdicionar(Model model) {
        PedidoRepositorio pedidoRepo = new PedidoRepositorio();
        FuncionarioRepositorio funcRepo = new FuncionarioRepositorio();

        model.addAttribute("pedidos", pedidoRepo.listarPedidos());
        model.addAttribute("funcionarios", funcRepo.listarOperacionais());
        model.addAttribute("montagem", new Montagem());

        return "form-montagem";
    }

    @PostMapping("/montagens/adicionar")
    public String adicionar(Montagem montagem) {
        MontagemRepositorio repo = new MontagemRepositorio();
        repo.salvar(montagem);
        return "redirect:/montagens";
    }

    @GetMapping("/montagens/editar")
    public String editarMontagem(@RequestParam int idPedido,
                                @RequestParam int idFuncionario,
                                Model model) {
        MontagemRepositorio montagemRepo = new MontagemRepositorio();
        Montagem montagem = montagemRepo.buscarPorIds(idFuncionario, idPedido);
        if (montagem != null) {
            montagem.setIdFuncionarioOriginal(idFuncionario);
            montagem.setIdPedidoOriginal(idPedido);
        }

        FuncionarioRepositorio funcRepo = new FuncionarioRepositorio();
        PedidoRepositorio pedidoRepo = new PedidoRepositorio();

        model.addAttribute("montagem", montagem);
        model.addAttribute("funcionarios", funcRepo.listarOperacionais());
        model.addAttribute("pedidos", pedidoRepo.listarPedidos());

        return "editar-montagem";
    }

    @PostMapping("/montagens/editar")
    public String salvarEdicao(@ModelAttribute Montagem montagem) {
        MontagemRepositorio repo = new MontagemRepositorio();
        repo.atualizar(montagem);
        return "redirect:/montagens";
    }
}