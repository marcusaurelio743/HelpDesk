package br.comHelpDesk.resource;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.comHelpDesk.domain.Tecnico;
import br.comHelpDesk.dtos.TecnicoDTO;
import br.comHelpDesk.services.TecnicoService;

@RestController
@RequestMapping(value = "/tecnicos")
public class TecnicoResource {
	
	@Autowired
	private TecnicoService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<TecnicoDTO> buscaPorId(@PathVariable Long id){
		Tecnico obj = service.buscaPorId(id);
		
		return ResponseEntity.ok().body(new TecnicoDTO(obj));
	}
	
	@GetMapping
	public ResponseEntity<List<TecnicoDTO>> buscatodos(){
		List<Tecnico> list = service.buscaTodos();
		List<TecnicoDTO> listDto = list.stream().map(obj -> new TecnicoDTO(obj)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listDto);
	}
	/*metodo alternativo no caso chame o tecnico repository de forma direta
	 * criando um metodo que retorna essa instancia no serviceTecnico */
	@GetMapping("/repository")
	public ResponseEntity<List<TecnicoDTO>> buscaTodos(){
		List<TecnicoDTO> listDtos = service.getRepository().
					findAll().stream().map(obj -> new TecnicoDTO(obj)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listDtos);
	}
	
	@PostMapping
	
	public ResponseEntity<TecnicoDTO> created( @Valid @RequestBody TecnicoDTO tecnicoDTO){
		Tecnico newobj = service.created(tecnicoDTO);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newobj.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<TecnicoDTO> update( @PathVariable Long id,@Valid @RequestBody TecnicoDTO tecnicoDTO){
		Tecnico oldObj = service.update(id, tecnicoDTO);
		return ResponseEntity.ok().body(new TecnicoDTO(oldObj));
	}

}
