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

import br.comHelpDesk.domain.Chamado;
import br.comHelpDesk.dtos.ChamadoDTO;
import br.comHelpDesk.services.ChamadoService;

@RestController
@RequestMapping(value = "/chamados")
public class ChamadoResource {
	@Autowired
	private ChamadoService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ChamadoDTO> buscaPorId(@PathVariable Long id){
		Chamado chamado = service.buscaPorId(id);
		
		return ResponseEntity.ok().body(new ChamadoDTO(chamado));
	}
	
	@GetMapping
	public ResponseEntity<List<ChamadoDTO>> buscarTodos(){
		List<Chamado> chamados = service.buscaTodos();
		List<ChamadoDTO> listDtos = chamados.stream().map(obj -> new ChamadoDTO(obj)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listDtos);
	}
	
	@PostMapping
	public ResponseEntity<ChamadoDTO> created(@Valid @RequestBody ChamadoDTO chamadoDTO){
		Chamado obj = service.created(chamadoDTO);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<ChamadoDTO> atualizar(@PathVariable Long id, @Valid @RequestBody ChamadoDTO chmDto){
		Chamado oldChamado = service.atualizar(id,chmDto);
		
		return ResponseEntity.ok().body(new ChamadoDTO(oldChamado));
	}

}
