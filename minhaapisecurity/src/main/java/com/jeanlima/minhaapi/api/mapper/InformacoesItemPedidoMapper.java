package com.jeanlima.minhaapi.api.mapper;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.jeanlima.minhaapi.api.dto.InformacaoItemPedidoDTO;
import com.jeanlima.minhaapi.model.ItemPedido;

@Component
public class InformacoesItemPedidoMapper {


    public List<InformacaoItemPedidoDTO> toItensPedidoDTO(List<ItemPedido> itens){
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
