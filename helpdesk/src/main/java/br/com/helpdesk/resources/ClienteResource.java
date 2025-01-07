package br.com.helpdesk.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.helpdesk.domain.Cliente;
import br.com.helpdesk.domain.dtos.ClienteDto;
import br.com.helpdesk.services.ClienteService; 

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {
	@Autowired
	private ClienteService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ClienteDto> buscaPorId(@PathVariable Long id){
		Cliente obj = service.buscaPorId(id);
		
		return ResponseEntity.ok().body(new ClienteDto(obj));
	}
	
	@GetMapping
	public ResponseEntity<List<ClienteDto>> ListaTodos(){
		List<Cliente> Clientes = service.listaTodos();
		List<ClienteDto> ClientesDto = Clientes.stream().map( x-> new ClienteDto(x)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(ClientesDto);
	}
	
	@PostMapping	
	public ResponseEntity<ClienteDto> Salvar(@Valid @RequestBody ClienteDto objDto){
		Cliente newObj = service.salvar(objDto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<ClienteDto> atualizar(@PathVariable Long id, @Valid @RequestBody ClienteDto objDto){
		Cliente obj = service.atualizar(id,objDto);
		
		return ResponseEntity.ok().body(new ClienteDto(obj));
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id){
		
		service.deletar(id);
		
	  return ResponseEntity.noContent().build();
	}

}
