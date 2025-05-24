package com.jeanlima.minhaapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.jeanlima.minhaapi.model.Produto;
import com.jeanlima.minhaapi.repository.ProdutoRepository;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto save(Produto produto){
        return produtoRepository.save(produto);
    }

    public void update(Integer id, Produto produto){
        produtoRepository
                .findById(id)
                .map( p -> {
                   produto.setId(p.getId());
                   produtoRepository.save(produto);
                   return produto;
                }).orElseThrow( () ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Produto não encontrado."));
    }

    public void delete(Integer id){
        produtoRepository
                .findById(id)
                .map( p -> {
                    produtoRepository.delete(p);
                    return Void.TYPE;
                }).orElseThrow( () ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Produto não encontrado."));
    }

    public Produto getById(Integer id){
        return produtoRepository
        .findById(id)
        .orElseThrow( () ->
        new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Produto não encontrado."));
    }

    public List<Produto> listProdutos(){
        return produtoRepository.findAll();
    }


    
}
