package com.jeanlima.minhaapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.jeanlima.minhaapi.api.dto.ClienteDTO;
import com.jeanlima.minhaapi.model.Cliente;
import com.jeanlima.minhaapi.repository.ClienteRepository;

@Service
public class ClienteService {


    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente save(ClienteDTO cliente){

        Cliente novoCliente = new Cliente();
        novoCliente.setNome(cliente.getNome());
        novoCliente.setCpf(cliente.getCpf());

        return clienteRepository.save(novoCliente);
    }

    public void update(Integer id, Cliente cliente){
        clienteRepository
                .findById(id)
                .map( p -> {
                   cliente.setId(p.getId());
                   clienteRepository.save(cliente);
                   return cliente;
                }).orElseThrow( () ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Cliente não encontrado."));
    }

    public void delete(Integer id){
        clienteRepository
                .findById(id)
                .map( p -> {
                    clienteRepository.delete(p);
                    return Void.TYPE;
                }).orElseThrow( () ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Cliente não encontrado."));
    }

    public Cliente getById(Integer id){
        return clienteRepository
        .findById(id)
        .orElseThrow( () ->
        new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Cliente não encontrado."));
    }

    public List<Cliente> listClientes(){
        return clienteRepository.findAll();
    }
    
}
