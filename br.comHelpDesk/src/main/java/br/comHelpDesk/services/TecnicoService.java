package br.comHelpDesk.services;

import java.util.List;
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

	public List<Tecnico> buscaTodos() {
		
		return repository.findAll();
	}
	/*metodo que me retorna a instância do TecnicoRepository com isso
	 * pode ter acesso aos metodos do jpa de forma direta*/
	public TecnicoRepository getRepository() {
		return repository;
	}

}
