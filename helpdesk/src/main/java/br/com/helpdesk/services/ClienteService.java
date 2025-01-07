package br.com.helpdesk.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.helpdesk.domain.Cliente;
import br.com.helpdesk.domain.Pessoa;
import br.com.helpdesk.domain.dtos.ClienteDto;
import br.com.helpdesk.repository.ClienteRepository;
import br.com.helpdesk.repository.PessoaRepository;
import br.com.helpdesk.services.exceptions.DataIntegrityViolationException;
import br.com.helpdesk.services.exceptions.ObjectNotFundException;

@Service
public class ClienteService {
	@Autowired
	private ClienteRepository repository;
	@Autowired
	private PessoaRepository pessoaRepository;
	
	public Cliente buscaPorId(Long id) {
		Optional<Cliente> obj = repository.findById(id);
		
		return obj.orElseThrow(()->new ObjectNotFundException("Objeto não encontrado id: "+id));
	}

	public List<Cliente> listaTodos() {
		
		return repository.findAll();
	}

	public Cliente salvar(ClienteDto objDto) {
		Cliente obj = new Cliente(objDto);
		obj.setId(null);
		
		validaPorCpfEmail(objDto);
		
		return repository.save(obj);
	}

	private void validaPorCpfEmail(ClienteDto objDto) {
		
		Optional<Pessoa> obj = pessoaRepository.findBycpf(objDto.getCpf());
		
		if(obj.isPresent() && obj.get().getId() != objDto.getId()) {
			throw new DataIntegrityViolationException("CPF já cadastrado no Sistema");
		}
		
		obj = pessoaRepository.findByemail(objDto.getEmail());
		if(obj.isPresent() && obj.get().getId() != objDto.getId()) {
			throw new DataIntegrityViolationException("E-mail já cadastrado no Sistema");
		}
	}

	public Cliente atualizar(Long id, @Valid ClienteDto objDto) {
		objDto.setId(id);
		Cliente oldObj = buscaPorId(id);
		validaPorCpfEmail(objDto);
		oldObj = new Cliente(objDto);
		
		return repository.save(oldObj);
	}

	public void deletar(Long id) {
		Cliente obj = buscaPorId(id);
		
		if(obj.getChamados().size() > 0) {
			throw new DataIntegrityViolationException("O Cliente possui Ordem de Serviço e não pode ser deletado!!");
		}else {
			
			repository.delete(obj);
		}
		
	}
}
