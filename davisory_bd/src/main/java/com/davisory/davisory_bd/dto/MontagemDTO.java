package com.davisory.davisory_bd.dto;

import com.davisory.davisory_bd.model.Funcionario;
import com.davisory.davisory_bd.model.Montagem;
import com.davisory.davisory_bd.model.Pedido;

public class MontagemDTO {
    private Montagem montagem;
    private Funcionario funcionario;
    private Pedido pedido;

    public MontagemDTO(Montagem montagem, Funcionario funcionario, Pedido pedido) {
        this.montagem = montagem;
        this.funcionario = funcionario;
        this.pedido = pedido;
    }

    public Montagem getMontagem() {
        return montagem;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public Pedido getPedido() {
        return pedido;
    }
}