package com.jeanlima.minhaapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jeanlima.minhaapi.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente,Integer>{

   
    List<Cliente> findByNomeLike(String nome);
    List<Cliente> findByNomeOrId(String nome, Integer id);
    boolean existsByNome(String nome);

  
    @Query(value = " select c from Cliente c where c.nome like %:nome% ")
    List<Cliente> encontrarPorNome(@Param("nome") String nome);

    //sql nativo
    @Query(value = " select * from cliente c where c.nome like %:nome% ",nativeQuery = true)
    List<Cliente> encontrarPorNomeMod(@Param("nome") String nome);

    @Query(value = " delete from Cliente c where c.nome =:nome")
    @Modifying //pois não é só consulta - transactional 
    void deletarPorNome(String nome);

    @Query(" select c from Cliente c left join fetch c.pedidos where c.id = :id  ")
    Cliente findClienteFetchPedidos( @Param("id") Integer id );

    
    
}
