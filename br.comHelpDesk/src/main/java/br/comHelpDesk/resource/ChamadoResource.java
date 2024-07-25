package br.comHelpDesk.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
