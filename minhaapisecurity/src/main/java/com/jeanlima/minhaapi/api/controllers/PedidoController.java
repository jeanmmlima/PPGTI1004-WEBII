
package com.jeanlima.minhaapi.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.jeanlima.minhaapi.api.dto.AtualizacaoStatusPedidoDTO;
import com.jeanlima.minhaapi.api.dto.InformacoesPedidoDTO;
import com.jeanlima.minhaapi.api.dto.PedidoDTO;
import com.jeanlima.minhaapi.api.mapper.InformacoesPedidoMapper;
import com.jeanlima.minhaapi.enums.StatusPedido;
import com.jeanlima.minhaapi.model.Pedido;
import com.jeanlima.minhaapi.service.PedidoService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService service;

    @Autowired
    private InformacoesPedidoMapper informacoesPedidoMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer save(@Valid @RequestBody PedidoDTO dto ){
        Pedido pedido = service.salvar(dto);
        return pedido.getId();
    }

    @Operation(summary = "Buscar pedido por ID", description = "Retorna os dados de um pedido a partir do ID informado.")
    @GetMapping("{id}")
    public InformacoesPedidoDTO getById( @PathVariable Integer id ){
        return service
                .obterPedidoCompleto(id)
                .map( p -> informacoesPedidoMapper.toInformacoesPedidoDTO(p) )
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado."));
    }

    

     @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateStatus(@PathVariable Integer id ,
                             @RequestBody AtualizacaoStatusPedidoDTO dto){
        String novoStatus = dto.getNovoStatus();
        service.atualizaStatus(id, StatusPedido.valueOf(novoStatus));
    }

    
}
