package com.jeanlima.minhaapi.helpers;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.jeanlima.minhaapi.api.dto.InformacaoItemPedidoDTO;
import com.jeanlima.minhaapi.api.dto.InformacoesPedidoDTO;
import com.jeanlima.minhaapi.model.ItemPedido;
import com.jeanlima.minhaapi.model.Pedido;


@Component
public class DTOHelper {

    public InformacoesPedidoDTO convertPedidoToInformacoesPedido(Pedido pedido){
        return InformacoesPedidoDTO
                .builder()
                .codigo(pedido.getId())
                .dataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .cpf(pedido.getCliente().getCpf())
                .nomeCliente(pedido.getCliente().getNome())
                .total(pedido.getTotal())
                .status(pedido.getStatus().name())
                .items(converterItensPedidoToDTO(pedido.getItens()))
                .build();
    }

    public List<InformacaoItemPedidoDTO> converterItensPedidoToDTO(List<ItemPedido> itens){
        if(CollectionUtils.isEmpty(itens)){
            return Collections.emptyList();
        }
        return itens.stream().map(
                item -> InformacaoItemPedidoDTO
                            .builder()
                            .descricaoProduto(item.getProduto().getDescricao())
                            .precoUnitario(item.getProduto().getPreco())
                            .quantidade(item.getQuantidade())
                            .build()
        ).collect(Collectors.toList());
    }
    
}
