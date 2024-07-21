package br.comHelpDesk.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.comHelpDesk.domain.Tecnico;
import br.comHelpDesk.services.TecnicoService;

@RestController
@RequestMapping(value = "/tecnicos")
public class TecnicoResource {
	
	@Autowired
	private TecnicoService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Tecnico> buscaPorId(@PathVariable Long id){
		Tecnico obj = service.buscaPorId(id);
		
		return ResponseEntity.ok().body(obj);
	}

}
