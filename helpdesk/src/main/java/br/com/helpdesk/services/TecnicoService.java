package br.com.helpdesk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.helpdesk.domain.Tecnico;
import br.com.helpdesk.domain.dtos.TecnicoDto;
import br.com.helpdesk.repository.TecnicoRepository;
import br.com.helpdesk.services.exceptions.ObjectNotFundException;

@Service
public class TecnicoService {
	@Autowired
	private TecnicoRepository repository;
	
	public Tecnico buscaPorId(Long id) {
		Optional<Tecnico> obj = repository.findById(id);
		
		return obj.orElseThrow(()->new ObjectNotFundException("Objeto não encontrado id: "+id));
	}

	public List<Tecnico> listaTodos() {
		
		return repository.findAll();
	}

	public Tecnico salvar(TecnicoDto objDto) {
		Tecnico obj = new Tecnico(objDto);
		obj.setId(null);
		
		return repository.save(obj);
	}
}
