package com.davisory.davisory_bd.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
        @RequestParam("codigoEntrega") String codigoEntrega,
        @RequestParam("quantidade") int quantidade,
        @RequestParam("precoUnitario") double precoUnitario,
        @RequestParam("idProduto") int idProduto,
        @RequestParam("idFuncionario") int idFuncionario,
        @RequestParam("cpfCnpjCliente") String cpfCnpjCliente
    ) {
        Pedido pedido = new Pedido();
        pedido.setDataPedido(new Timestamp(new Date().getTime()));
        pedido.setCodigoEntregaPedido(codigoEntrega);
        pedido.setQuantidadePedido(quantidade);
        pedido.setPrecoUnitarioPedido(precoUnitario);
        pedido.setIdProduto(idProduto);
        pedido.setIdFuncionario(idFuncionario);
        pedido.setCpfCnpjCliente(cpfCnpjCliente);
        pedidoRepositorio.inserir(pedido);
        return "redirect:/pedidos";
    }

    @GetMapping("/pedidos/editar/{id}")
    public String editarPedido(@PathVariable("id") int id, Model model) {
        Pedido pedido = pedidoRepositorio.buscarPorId(id);
        model.addAttribute("pedido", pedido);
        model.addAttribute("produtos", produtoRepositorio.listarProdutos());
        model.addAttribute("funcionarios", funcionarioRepositorio.listarTodosFuncionarios());
        model.addAttribute("clientes", clienteRepositorio.listarTodos());
        return "editarpedido";
    }

    @PostMapping("/pedidos/atualizar")
    public String atualizarPedido(
        @RequestParam("id") int id,
        @RequestParam("codigoEntrega") String codigoEntrega,
        @RequestParam("quantidade") int quantidade,
        @RequestParam("precoUnitario") double precoUnitario,
        @RequestParam("idProduto") int idProduto,
        @RequestParam("idFuncionario") int idFuncionario,
        @RequestParam("cpfCnpjCliente") String cpfCnpjCliente
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
}