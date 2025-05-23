package com.jeanlima.minhaapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jeanlima.minhaapi.model.Cliente;
import com.jeanlima.minhaapi.model.Pedido;



public interface PedidoRepository extends JpaRepository<Pedido,Integer>{

    List<Pedido> findByCliente(Cliente cliente);
    //Optional<Pedido> findByIdFetchItens(Integer id);
    @Query(" select p from Pedido p left join fetch p.itens where p.id = :id ")
    Optional<Pedido> findByIdFetchItens(@Param("id") Integer id);
}
