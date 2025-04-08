package com.davisory.davisory_bd.controller;

import com.davisory.davisory_bd.dao.FuncionarioRepositorio;
import com.davisory.davisory_bd.dao.PedidoRepositorio;
import com.davisory.davisory_bd.dao.ProdutoRepositorio;
import com.davisory.davisory_bd.dto.PedidoDetalhado;
import com.davisory.davisory_bd.model.Funcionario;
import com.davisory.davisory_bd.model.Pedido;
import com.davisory.davisory_bd.model.Produto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PedidoController {

    PedidoRepositorio pedidoRepositorio = new PedidoRepositorio();
    ProdutoRepositorio produtoRepositorio = new ProdutoRepositorio();
    FuncionarioRepositorio funcionarioRepositorio = new FuncionarioRepositorio();

    @GetMapping("/pedidos")
    public String exibirPaginaPedidos(Model model) {
        List<Pedido> pedidos = pedidoRepositorio.listarPedidos();
        List<PedidoDetalhado> pedidosDetalhados = new ArrayList<>();

        for (Pedido pedido : pedidos) {
            Produto produto = produtoRepositorio.buscarPorId(pedido.getIdProduto());
            Funcionario funcionario = funcionarioRepositorio.buscarPorId(pedido.getIdFuncionario());

            PedidoDetalhado dto = new PedidoDetalhado(pedido, produto, funcionario);
            pedidosDetalhados.add(dto);
        }

        model.addAttribute("pedidosDetalhados", pedidosDetalhados);
        return "pedidos";
    }
}