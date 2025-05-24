package com.jeanlima.minhaapi.service.impl;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.jeanlima.minhaapi.api.dto.InformacaoItemPedidoDTO;
import com.jeanlima.minhaapi.api.dto.InformacoesPedidoDTO;
import com.jeanlima.minhaapi.api.dto.ItemPedidoDTO;
import com.jeanlima.minhaapi.api.dto.PedidoDTO;
import com.jeanlima.minhaapi.enums.StatusPedido;
import com.jeanlima.minhaapi.exceptions.PedidoNaoEncontradoException;
import com.jeanlima.minhaapi.exceptions.RegraNegocioException;
import com.jeanlima.minhaapi.model.Cliente;
import com.jeanlima.minhaapi.model.ItemPedido;
import com.jeanlima.minhaapi.model.Pedido;
import com.jeanlima.minhaapi.model.Produto;
import com.jeanlima.minhaapi.repository.ClienteRepository;
import com.jeanlima.minhaapi.repository.ItemPedidoRepository;
import com.jeanlima.minhaapi.repository.PedidoRepository;
import com.jeanlima.minhaapi.repository.ProdutoRepository;
import com.jeanlima.minhaapi.service.PedidoService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {
    
    private final PedidoRepository repository;
    private final ClienteRepository clientesRepository;
    private final ProdutoRepository produtosRepository;
    private final ItemPedidoRepository itemsPedidoRepository;

    @Override
    @Transactional
    public Pedido salvar(PedidoDTO dto) {
        Integer idCliente = dto.getCliente();
        Cliente cliente = clientesRepository
                .findById(idCliente)
                .orElseThrow(() -> new RegraNegocioException("Código de cliente inválido."));

        Pedido pedido = new Pedido();
        //pedido.setTotal(dto.getTotal());
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente(cliente);
        pedido.setStatus(StatusPedido.REALIZADO);

        List<ItemPedido> itemsPedido = converterItems(pedido, dto.getItems());
        repository.save(pedido);
        itemsPedidoRepository.saveAll(itemsPedido);
        pedido.setItens(itemsPedido);
        pedido.setTotal(BigDecimal.valueOf(pedido.calculateTotalPedido()));



        return pedido;
    }
    private List<ItemPedido> converterItems(Pedido pedido, List<ItemPedidoDTO> items){
        if(items.isEmpty()){
            throw new RegraNegocioException("Não é possível realizar um pedido sem items.");
        }

        return items
                .stream()
                .map( dto -> {
                    Integer idProduto = dto.getProduto();
                    Produto produto = produtosRepository
                            .findById(idProduto)
                            .orElseThrow(
                                    () -> new RegraNegocioException(
                                            "Código de produto inválido: "+ idProduto
                                    ));

                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setQuantidade(dto.getQuantidade());
                    itemPedido.setPedido(pedido);
                    itemPedido.setProduto(produto);
                    return itemPedido;
                }).collect(Collectors.toList());

    }

    @Override
    public Optional<Pedido> obterPedidoCompleto(Integer id) {
        return repository.findByIdFetchItens(id);
    }
    @Override
    public void atualizaStatus(Integer id, StatusPedido statusPedido) {
        repository
        .findById(id)
        .map( pedido -> {
            pedido.setStatus(statusPedido);
            return repository.save(pedido);
        }).orElseThrow(() -> new PedidoNaoEncontradoException() );
    }

    
    
    
}
