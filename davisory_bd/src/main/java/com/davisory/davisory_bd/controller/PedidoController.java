package com.davisory.davisory_bd.controller;

import com.davisory.davisory_bd.dao.PedidoRepositorio;
import com.davisory.davisory_bd.model.Pedido;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class PedidoController {

    PedidoRepositorio pedidoRepositorio = new PedidoRepositorio();

    @GetMapping("/pedidos")
    public String exibirPaginaPedidos(Model model) {
        List<Pedido> pedidos = pedidoRepositorio.listarPedidos();
        model.addAttribute("pedidos", pedidos);
        return "pedidos";
    }
}
