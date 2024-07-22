package br.comHelpDesk.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.comHelpDesk.domain.Tecnico;
import br.comHelpDesk.repositores.TecnicoRepository;
import br.comHelpDesk.services.exeptions.ObjectNotFundExeption;

@Service
public class TecnicoService {
	@Autowired
	private TecnicoRepository repository;
	
	public Tecnico buscaPorId(Long id) {
		Optional<Tecnico> obj = repository.findById(id);
		
		return obj.orElseThrow(()-> new ObjectNotFundExeption("Objeto não encontrado id "+id));
	}

}
