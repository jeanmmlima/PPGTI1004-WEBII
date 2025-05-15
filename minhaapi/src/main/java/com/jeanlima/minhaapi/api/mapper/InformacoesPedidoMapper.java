package com.jeanlima.minhaapi.api.mapper;

import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jeanlima.minhaapi.api.dto.InformacoesPedidoDTO;
import com.jeanlima.minhaapi.model.Pedido;

@Component
public class InformacoesPedidoMapper {

    @Autowired
    private InformacoesItemPedidoMapper informacoesItemPedidoMapper;

    public InformacoesPedidoDTO toInformacoesPedidoDTO(Pedido pedido){
        return InformacoesPedidoDTO
                .builder()
                .codigo(pedido.getId())
                .dataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .cpf(pedido.getCliente().getCpf())
                .nomeCliente(pedido.getCliente().getNome())
                .total(pedido.getTotal())
                .status(pedido.getStatus().name())
                .items(informacoesItemPedidoMapper.toItensPedidoDTO(pedido.getItens()))
                .build();
    }
    
}
