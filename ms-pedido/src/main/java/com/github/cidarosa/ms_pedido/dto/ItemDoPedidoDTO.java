package com.github.cidarosa.ms_pedido.dto;


import com.github.cidarosa.ms_pedido.entities.ItemDoPedido;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ItemDoPedidoDTO {

    private long id;

    @NotNull(message = "Quantidade é requerida")
    @Positive(message = "A quantidade deve ser um número positivo")
    private Integer quantidade;

    @NotEmpty(message = "Descrição é requerida")
    private String descricao;

    @NotEmpty(message = "Valor unitario requerido")
    @Positive(message = "Valor unitario deve ser um numero positivo")
    private BigDecimal valorUnitario;

    public ItemDoPedidoDTO(ItemDoPedido entity) {
        id = entity.getId();
        valorUnitario = entity.getValorUnitario();
        descricao = entity.getDescricao();
        quantidade = entity.getQuantidade();
    }
}
