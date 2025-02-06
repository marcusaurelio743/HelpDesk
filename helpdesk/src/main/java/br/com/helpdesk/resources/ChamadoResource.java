package br.com.helpdesk.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.helpdesk.domain.Chamado;
import br.com.helpdesk.domain.dtos.ChamadoDto;
import br.com.helpdesk.services.ChamadoService;

@RestController
@RequestMapping(value = "/chamados")
public class ChamadoResource {
	@Autowired
	private ChamadoService service;
	
	@GetMapping("/{id}")
	public ResponseEntity<ChamadoDto> findById(@PathVariable Long id){
		
		Chamado obj = service.findById(id);
		
		return ResponseEntity.ok().body(new ChamadoDto(obj));
	}
	
	@GetMapping
	public ResponseEntity<List<ChamadoDto>> findAll(){
		List<Chamado> chamados = service.findAll();
		List<ChamadoDto> chamadoDtos = chamados.stream().map(x-> new ChamadoDto(x)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(chamadoDtos);
	}

}
