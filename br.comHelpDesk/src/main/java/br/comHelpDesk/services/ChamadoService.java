package br.comHelpDesk.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.comHelpDesk.domain.Chamado;
import br.comHelpDesk.repositores.ChamadoRepository;
import br.comHelpDesk.services.exeptions.ObjectNotFundExeption;

@Service
public class ChamadoService {
	@Autowired
	private ChamadoRepository repository;
	
	public Chamado buscaPorId(Long id) {
		Optional<Chamado> obj = repository.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFundExeption("Objeto não encontrado id: "+id));
	}

}
