package br.com.pjspring.ws.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.pjspring.ws.model.Cliente;

@Repository
public  interface ClienteRepository extends JpaRepository<Cliente, Integer>{

}
