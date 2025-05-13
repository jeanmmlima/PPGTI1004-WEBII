package com.jeanlima.minhaapi.service;

import java.util.Optional;

import com.jeanlima.minhaapi.api.dto.PedidoDTO;
import com.jeanlima.minhaapi.enums.StatusPedido;
import com.jeanlima.minhaapi.model.Pedido;



public interface PedidoService {
    Pedido salvar( PedidoDTO dto );
    Optional<Pedido> obterPedidoCompleto(Integer id);
    void atualizaStatus(Integer id, StatusPedido statusPedido);
    
}
