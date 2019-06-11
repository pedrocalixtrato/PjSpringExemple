package br.com.pjspring.ws.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.pjspring.ws.model.Cliente;

@Repository
public  interface ClienteDao extends JpaRepository<Cliente, Integer>{

}
