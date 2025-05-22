package com.davisory.davisory_bd.controller;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.davisory.davisory_bd.dao.ClienteRepositorio;
import com.davisory.davisory_bd.dao.FuncionarioRepositorio;
import com.davisory.davisory_bd.dao.PedidoRepositorio;
import com.davisory.davisory_bd.dao.ProdutoRepositorio;
import com.davisory.davisory_bd.dto.PedidoDetalhado;
import com.davisory.davisory_bd.model.Funcionario;
import com.davisory.davisory_bd.model.Pedido;
import com.davisory.davisory_bd.model.Produto;

@Controller
public class PedidoController {

    PedidoRepositorio pedidoRepositorio = new PedidoRepositorio();
    ProdutoRepositorio produtoRepositorio = new ProdutoRepositorio();
    FuncionarioRepositorio funcionarioRepositorio = new FuncionarioRepositorio();
    ClienteRepositorio clienteRepositorio = new ClienteRepositorio();

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

    @GetMapping("/pedidos/adicionar")
    public String exibirFormularioAdicao(Model model) {
        model.addAttribute("pedido", new Pedido());
        model.addAttribute("produtos", produtoRepositorio.listarProdutos());
        model.addAttribute("funcionarios", funcionarioRepositorio.listarTodosFuncionarios());
        model.addAttribute("clientes", clienteRepositorio.listarTodos());
        return "adicionarpedido";
    }

    @PostMapping("/pedidos/adicionar")
    public String adicionarPedido(
        @RequestParam String codigoEntrega,
        @RequestParam int quantidade,
        @RequestParam double precoUnitario,
        @RequestParam int idProduto,
        @RequestParam int idFuncionario,
        @RequestParam String cpfCnpjCliente
    ) {
        Pedido pedido = new Pedido();
        pedido.setDataPedido(new Timestamp(new Date().getTime()));
        pedido.setCodigoEntregaPedido(codigoEntrega);
        pedido.setQuantidadePedido(quantidade);
        pedido.setPrecoUnitarioPedido(precoUnitario);
        pedido.setIdProduto(idProduto);
        pedido.setIdFuncionario(idFuncionario);
        pedido.setCpfCnpjCliente(cpfCnpjCliente);
        try {
            pedidoRepositorio.realizarPedidoComProcedure(pedido);
        } catch (SQLException e) {
            e.printStackTrace();
            return "adicionarpedido";
        }
        return "redirect:/pedidos";
    }

    @GetMapping("/pedidos/editar/{id}")
    public String editarPedido(@PathVariable int id, Model model) {
        Pedido pedido = pedidoRepositorio.buscarPorId(id);
        model.addAttribute("pedido", pedido);
        model.addAttribute("produtos", produtoRepositorio.listarProdutos());
        model.addAttribute("funcionarios", funcionarioRepositorio.listarTodosFuncionarios());
        model.addAttribute("clientes", clienteRepositorio.listarTodos());
        return "editarpedido";
    }

    @PostMapping("/pedidos/atualizar")
    public String atualizarPedido(
        @RequestParam int id,
        @RequestParam String codigoEntrega,
        @RequestParam int quantidade,
        @RequestParam double precoUnitario,
        @RequestParam int idProduto,
        @RequestParam int idFuncionario,
        @RequestParam String cpfCnpjCliente
    ) {
        Pedido pedido = new Pedido();
        pedido.setIdPedido(id);
        pedido.setCodigoEntregaPedido(codigoEntrega);
        pedido.setQuantidadePedido(quantidade);
        pedido.setPrecoUnitarioPedido(precoUnitario);
        pedido.setIdProduto(idProduto);
        pedido.setIdFuncionario(idFuncionario);
        pedido.setCpfCnpjCliente(cpfCnpjCliente);
        pedidoRepositorio.atualizar(pedido);
        return "redirect:/pedidos";
    }

    @GetMapping("/pedidos/{id}")
    public String verPedido(@PathVariable int id, Model model) {
        Pedido pedido = pedidoRepositorio.buscarPorId(id);
        Produto produto = produtoRepositorio.buscarPorId(pedido.getIdProduto());
        Funcionario funcionario = funcionarioRepositorio.buscarPorId(pedido.getIdFuncionario());
        model.addAttribute("pedido", pedido);
        model.addAttribute("produto", produto);
        model.addAttribute("funcionario", funcionario);
        return "pedido_detalhado";
    }

    @GetMapping("/pedidos/grafico")
    public String exibirGraficoPedidosPorFuncionario(Model model) {
        List<Pedido> pedidos = pedidoRepositorio.listarPedidos();
        Map<String, Integer> pedidosPorFuncionario = new HashMap<>();
        for (Pedido pedido : pedidos) {
            Funcionario funcionario = funcionarioRepositorio.buscarPorId(pedido.getIdFuncionario());
            String nomeFuncionario = funcionario != null ? funcionario.getNomeFuncionario() : "Desconhecido";
            pedidosPorFuncionario.put(nomeFuncionario, pedidosPorFuncionario.getOrDefault(nomeFuncionario, 0) + 1);
        }
        model.addAttribute("pedidosPorFuncionario", pedidosPorFuncionario);
        return "graficoPedidosPorFuncionario";
    }
}