package br.com.helpdesk.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.helpdesk.domain.Pessoa;
import br.com.helpdesk.domain.Tecnico;
import br.com.helpdesk.domain.dtos.TecnicoDto;
import br.com.helpdesk.repository.PessoaRepository;
import br.com.helpdesk.repository.TecnicoRepository;
import br.com.helpdesk.services.exceptions.DataIntegrityViolationException;
import br.com.helpdesk.services.exceptions.ObjectNotFundException;

@Service
public class TecnicoService {
	@Autowired
	private TecnicoRepository repository;
	@Autowired
	private PessoaRepository pessoaRepository;
	
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
		
		validaPorCpfEmail(objDto);
		
		return repository.save(obj);
	}

	private void validaPorCpfEmail(TecnicoDto objDto) {
		
		Optional<Pessoa> obj = pessoaRepository.findBycpf(objDto.getCpf());
		
		if(obj.isPresent() && obj.get().getId() != objDto.getId()) {
			throw new DataIntegrityViolationException("CPF já cadastrado no Sistema");
		}
		
		obj = pessoaRepository.findByemail(objDto.getEmail());
		if(obj.isPresent() && obj.get().getId() != objDto.getId()) {
			throw new DataIntegrityViolationException("E-mail já cadastrado no Sistema");
		}
	}

	public Tecnico atualizar(Long id, @Valid TecnicoDto objDto) {
		objDto.setId(id);
		Tecnico oldObj = buscaPorId(id);
		validaPorCpfEmail(objDto);
		oldObj = new Tecnico(objDto);
		
		return repository.save(oldObj);
	}
}
