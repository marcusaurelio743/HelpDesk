package br.com.helpdesk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.helpdesk.domain.Chamado;
import br.com.helpdesk.repository.ChamadoRepository;
import br.com.helpdesk.services.exceptions.ObjectNotFundException;

@Service
public class ChamadoService {
	@Autowired
	private ChamadoRepository chamadoRepository;
	
	public Chamado findById(Long id) {
		Optional<Chamado> obj = chamadoRepository.findById(id);
		
		return obj.orElseThrow( () -> new ObjectNotFundException("Objeto não encontrado id: "+id));
	}
	public List<Chamado> findAll(){
		return chamadoRepository.findAll();
	}

}
