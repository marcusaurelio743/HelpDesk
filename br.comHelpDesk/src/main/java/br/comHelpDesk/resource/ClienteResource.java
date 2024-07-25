package br.comHelpDesk.resource;

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

import br.comHelpDesk.domain.Cliente;
import br.comHelpDesk.dtos.ClienteDTO;
import br.comHelpDesk.services.ClienteService;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {
	
	@Autowired
	private ClienteService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ClienteDTO> buscaPorId(@PathVariable Long id){
		Cliente obj = service.buscaPorId(id);
		
		return ResponseEntity.ok().body(new ClienteDTO(obj));
	}
	
	@GetMapping
	public ResponseEntity<List<ClienteDTO>> buscatodos(){
		List<Cliente> list = service.buscaTodos();
		List<ClienteDTO> listDto = list.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listDto);
	}
	/*metodo alternativo no caso chame o Cliente repository de forma direta
	 * criando um metodo que retorna essa instancia no serviceCliente */
	@GetMapping("/repository")
	public ResponseEntity<List<ClienteDTO>> buscaTodos(){
		List<ClienteDTO> listDtos = service.getRepository().
					findAll().stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listDtos);
	}
	
	@PostMapping
	
	public ResponseEntity<ClienteDTO> created( @Valid @RequestBody ClienteDTO ClienteDTO){
		Cliente newobj = service.created(ClienteDTO);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newobj.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<ClienteDTO> update( @PathVariable Long id,@Valid @RequestBody ClienteDTO ClienteDTO){
		Cliente oldObj = service.update(id, ClienteDTO);
		return ResponseEntity.ok().body(new ClienteDTO(oldObj));
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<ClienteDTO> Deletar(@PathVariable Long id){
		
		service.Deletar(id);
		
		return ResponseEntity.noContent().build();
	}
	

}
