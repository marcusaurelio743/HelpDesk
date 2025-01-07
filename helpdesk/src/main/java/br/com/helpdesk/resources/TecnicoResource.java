package br.com.helpdesk.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.helpdesk.domain.Tecnico;
import br.com.helpdesk.domain.dtos.TecnicoDto;
import br.com.helpdesk.services.TecnicoService;

@RestController
@RequestMapping(value = "/tecnicos")
public class TecnicoResource {
	@Autowired
	private TecnicoService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<TecnicoDto> buscaPorId(@PathVariable Long id){
		Tecnico obj = service.buscaPorId(id);
		
		return ResponseEntity.ok().body(new TecnicoDto(obj));
	}
	
	@GetMapping
	public ResponseEntity<List<TecnicoDto>> ListaTodos(){
		List<Tecnico> tecnicos = service.listaTodos();
		List<TecnicoDto> tecnicosDto = tecnicos.stream().map( x-> new TecnicoDto(x)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(tecnicosDto);
	}
	
	@PostMapping	
	public ResponseEntity<TecnicoDto> Salvar(@Valid @RequestBody TecnicoDto objDto){
		Tecnico newObj = service.salvar(objDto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}

}
