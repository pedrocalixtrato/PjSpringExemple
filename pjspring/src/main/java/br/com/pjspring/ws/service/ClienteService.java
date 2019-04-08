package br.com.pjspring.ws.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pjspring.ws.model.Cliente;
import br.com.pjspring.ws.repository.ClienteRepository;

@Service
public class ClienteService {
		
		@Autowired
		ClienteRepository clienteRepository;
	
				
		//Negocios
		public Cliente cadastrar(Cliente cliente) {								
			return clienteRepository.save(cliente);
		}		
		
		public Collection<Cliente> buscarTodos(){			
			return clienteRepository.findAll();
		}
		
		public void excluir (Cliente cliente) {
			clienteRepository.delete(cliente);
		}
		
		public Cliente buscarPorId(Integer id) {		
			return clienteRepository.getOne(id);
		}
	//Pode Ser feito sem retorno desta forma
	/*	private void alterar(Cliente cliente) {
			clientes.put(cliente.getId(), cliente);
		}*/
		
		public Cliente alterar(Cliente cliente) {
			return clienteRepository.save(cliente);
		}

}
