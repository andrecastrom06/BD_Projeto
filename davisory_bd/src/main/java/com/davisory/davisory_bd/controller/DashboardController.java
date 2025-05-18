    package com.davisory.davisory_bd.controller;

    import org.springframework.stereotype.Controller;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RequestParam;
    import org.springframework.web.bind.annotation.ResponseBody;

import com.davisory.davisory_bd.dao.AtendimentoRepositorio;
import com.davisory.davisory_bd.dao.ClienteRepositorio;
import com.davisory.davisory_bd.dao.FuncionarioRepositorio;
import com.davisory.davisory_bd.dao.PedidoRepositorio;
import com.davisory.davisory_bd.dao.ProdutoRepositorio;

import java.time.LocalDate;
    import java.util.ArrayList;
    import java.util.HashMap;
    import java.util.Map;

    @Controller
    @RequestMapping("/dashboard")
    public class DashboardController {

        private final PedidoRepositorio pedidoRepositorio = new PedidoRepositorio(); // direto, sem @Autowired

        @GetMapping
        public String abrirDashboard() {
            return "dashboard"; // nome do HTML
        }

        @GetMapping("/dados")
@ResponseBody
public Map<String, Object> obterDadosDashboard(
        @RequestParam String entidade,
        @RequestParam String tipo,
        @RequestParam(required = false) String inicio,
        @RequestParam(required = false) String fim
) {
    Map<String, Object> resposta = new HashMap<>();

    if (inicio == null || inicio.isEmpty() || fim == null || fim.isEmpty()) {
        resposta.put("erro", "Datas de início e fim são obrigatórias.");
        return resposta;
    }

    LocalDate dataInicio;
    LocalDate dataFim;

    try {
        dataInicio = LocalDate.parse(inicio);
        dataFim = LocalDate.parse(fim);
    } catch (Exception e) {
        resposta.put("erro", "Formato de data inválido.");
        return resposta;
    }

    if (entidade.equals("pedidos")) {
        Map<String, Integer> dados = pedidoRepositorio.contarPedidosPorDia(dataInicio, dataFim);
        resposta.put("label", "Pedidos por dia");
        resposta.put("labels", new ArrayList<>(dados.keySet()));
        resposta.put("valores", new ArrayList<>(dados.values()));
    }

    else if (entidade.equals("atendimentos")) {
    AtendimentoRepositorio atendimentoRepo = new AtendimentoRepositorio();
    Map<String, Integer> dados = atendimentoRepo.contarAtendimentosPorDia(dataInicio, dataFim);
    resposta.put("label", "Atendimentos por dia");
    resposta.put("labels", new ArrayList<>(dados.keySet()));
    resposta.put("valores", new ArrayList<>(dados.values()));
}
    return resposta;
}
@GetMapping("/resumo")
@ResponseBody
public Map<String, Object> obterResumoDashboard() {
    Map<String, Object> resposta = new HashMap<>();

    ClienteRepositorio clienteRepositorio = new ClienteRepositorio();
    PedidoRepositorio pedidoRepositorio = new PedidoRepositorio();
    ProdutoRepositorio produtoRepositorio = new ProdutoRepositorio();
    FuncionarioRepositorio funcionarioRepositorio = new FuncionarioRepositorio();

    int totalClientes = clienteRepositorio.contarClientes();
    int totalPedidos = pedidoRepositorio.contarPedidos();
    String produtoMaisVendido = produtoRepositorio.buscarProdutoMaisVendido();
    int totalFuncionariosAtivos = funcionarioRepositorio.contarFuncionariosAtivos();
    int totalProdutos = produtoRepositorio.contarProdutos();

    resposta.put("totalClientes", totalClientes);
    resposta.put("totalPedidos", totalPedidos);
    resposta.put("produtoMaisVendido", produtoMaisVendido);
    resposta.put("totalFuncionariosAtivos", totalFuncionariosAtivos);
    resposta.put("totalProdutos", totalProdutos);

    return resposta;
}




    }
