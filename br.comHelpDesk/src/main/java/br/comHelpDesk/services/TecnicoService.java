package br.comHelpDesk.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.comHelpDesk.domain.Tecnico;
import br.comHelpDesk.repositores.TecnicoRepository;

@Service
public class TecnicoService {
	@Autowired
	private TecnicoRepository repository;
	
	public Tecnico buscaPorId(Long id) {
		Optional<Tecnico> obj = repository.findById(id);
		
		return obj.orElse(null);
	}

}
