package br.comHelpDesk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.comHelpDesk.domain.Pessoa;
import br.comHelpDesk.domain.Tecnico;
import br.comHelpDesk.dtos.TecnicoDTO;
import br.comHelpDesk.repositores.PessoaRepository;
import br.comHelpDesk.repositores.TecnicoRepository;
import br.comHelpDesk.services.exeptions.DataIntegrityViolationException;
import br.comHelpDesk.services.exeptions.ObjectNotFundExeption;

@Service
public class TecnicoService {
	@Autowired
	private TecnicoRepository repository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	public Tecnico buscaPorId(Long id) {
		Optional<Tecnico> obj = repository.findById(id);
		
		return obj.orElseThrow(()-> new ObjectNotFundExeption("Objeto não encontrado id "+id));
	}

	public List<Tecnico> buscaTodos() {
		
		return repository.findAll();
	}
	/*metodo que me retorna a instância do TecnicoRepository com isso
	 * pode ter acesso aos metodos do jpa de forma direta*/
	public TecnicoRepository getRepository() {
		return repository;
	}

	public Tecnico created(TecnicoDTO tecnicoDTO) {
		tecnicoDTO.setId(null);
		validaPorCpfEmail(tecnicoDTO);
		Tecnico newObj = new Tecnico(tecnicoDTO);
		return repository.save(newObj);
	}

	private void validaPorCpfEmail(TecnicoDTO tecnicoDTO) {
		Optional<Pessoa> obj = pessoaRepository.findBycpf(tecnicoDTO.getCpf());
		if(obj.isPresent() && obj.get().getId() != tecnicoDTO.getId()) {
			throw new DataIntegrityViolationException("Cpf já cadastrado no Sistema!!");
		}
		
		
		obj = pessoaRepository.findByemail(tecnicoDTO.getEmail());
		
		if(obj.isPresent() && obj.get().getId() != tecnicoDTO.getId()) {
			throw new DataIntegrityViolationException("Email já cadastrado no Sistema!!");
		}
		
		
	}

}
