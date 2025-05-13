package com.jeanlima.camadas.repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.jeanlima.camadas.model.Produto;

@Repository
public class ProdutoRepository {

    private final Map<Long, Produto> produtos = new HashMap<>();

    public ProdutoRepository() {
        // Produtos simulados
        produtos.put(1L, new Produto(1L, "Notebook", new BigDecimal("4500.00")));
        produtos.put(2L, new Produto(2L, "Smartphone", new BigDecimal("2500.00")));
        produtos.put(3L, new Produto(3L, "Monitor", new BigDecimal("900.00")));
    }

    public Optional<Produto> findById(Long id) {
        return Optional.ofNullable(produtos.get(id));
    }

    public List<Produto> findAll() {
        return new ArrayList<>(produtos.values());
    }

    public Produto save(Produto produto) {
        if (produto.getId() == null) {
            Long newId = produtos.keySet().stream().max(Long::compareTo).orElse(0L) + 1;
            produto.setId(newId);
        }
        produtos.put(produto.getId(), produto);
        return produto;
    }

    public void deleteById(Long id) {
        produtos.remove(id);
    }

    
}
