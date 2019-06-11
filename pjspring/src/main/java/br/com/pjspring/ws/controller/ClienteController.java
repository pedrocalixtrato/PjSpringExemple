package br.com.pjspring.ws.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.pjspring.ws.model.Cliente;
import br.com.pjspring.ws.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	ClienteService clienteService;
	
	
	//End points
	@RequestMapping(method=RequestMethod.POST, value="", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Cliente> salvarCliente(@RequestBody Cliente cliente) {
		
		Cliente clienteCadastrado = clienteService.salvar(cliente);
		clienteService.enviarWpp();
		return new ResponseEntity<Cliente>(clienteCadastrado, HttpStatus.CREATED);
	}
	@CrossOrigin
	@RequestMapping(method=RequestMethod.GET, value="", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Cliente>> buscarTodosClientes(){
		
		Collection<Cliente> clientesBuscados = clienteService.buscarTodos();

		
		return new ResponseEntity<>(clientesBuscados, HttpStatus.OK);
	}

	@RequestMapping(method=RequestMethod.PUT, value="", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Cliente> alterarCliente(@RequestBody Cliente cliente) {

		Cliente clienteAlterado = clienteService.salvar(cliente);
		return new ResponseEntity<Cliente>(clienteAlterado, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/{id}")
	public ResponseEntity<Cliente> excluirCliente (@PathVariable Integer id){
		
		Cliente clienteEncontrado = clienteService.buscarPorId(id);		
		if(clienteEncontrado==null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		clienteService.excluir(clienteEncontrado);
		return new ResponseEntity<>(HttpStatus.OK);

	}
	@CrossOrigin
	@RequestMapping(method = RequestMethod.GET, value = "/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Cliente> obtterCliente(@PathVariable Integer id){

		Cliente cliente = clienteService.buscarPorId(id);

//				new Cliente();
//		cliente.setId(1);
//		cliente.setNome("Dados mocado");

		if(cliente == null){return new ResponseEntity<>(HttpStatus.NOT_FOUND);}

		return new ResponseEntity<>(cliente, HttpStatus.OK);
	}


	

}
