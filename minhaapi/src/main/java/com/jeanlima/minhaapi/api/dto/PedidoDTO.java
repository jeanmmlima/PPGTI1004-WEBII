package com.jeanlima.minhaapi.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

/*
 * {
    "cliente" : 1,
    "items" : [
        {
            "produto": 1,
            "quantidade": 10
        }
        
    ]
}
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Dados para criação de um pedido")
public class PedidoDTO {

    @NotNull(message = "Inserir um Cliente válido!")
    @Schema(description = "ID do cliente", example = "1")
    private Integer cliente;

    private List<ItemPedidoDTO> items;
}
