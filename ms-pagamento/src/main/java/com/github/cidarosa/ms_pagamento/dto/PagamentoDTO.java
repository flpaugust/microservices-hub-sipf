package com.github.cidarosa.ms_pagamento.dto;

import com.github.cidarosa.ms_pagamento.entity.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PagamentoDTO {

    private Long id;
    @NotNull(message = "Campo obrigatório")
    @Positive(message = "O valor do pagamento deve ser um número positivo")
    private BigDecimal valor;

    @Size(min = 2, max = 100, message = "O nome deve ter entre 2 e 100 caracteres")
    private String nome;

    @Size(min = 16, max = 19, message = "O número do cartão deve ter entre 16 e 19 caracteres")
    private String numeroDoCartao;

    @Size(min = 5, max = 5, message = "A validade do cartão deve ter 5 caracteres")
    private String validade;

    @Size(min = 3, max = 3, message = "O código de segurança deve ter 3 caracteres")
    private String codigoDeSeguranca;

    @Enumerated(EnumType.STRING)
    private Status status;

    @NotNull(message = "O Id do pedido não pode ser nulo")
    private Long pedidoId;

    @NotNull(message = "A forma de pagamento é requerida")
    private Long formaDePagamentoId;
}
