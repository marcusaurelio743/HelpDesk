package br.comHelpDesk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.comHelpDesk.domain.Cliente;
import br.comHelpDesk.domain.Pessoa;
import br.comHelpDesk.dtos.ClienteDTO;
import br.comHelpDesk.repositores.ClienteRepository;
import br.comHelpDesk.repositores.PessoaRepository;
import br.comHelpDesk.services.exeptions.DataIntegrityViolationException;
import br.comHelpDesk.services.exeptions.ObjectNotFundExeption;

@Service
public class ClienteService {
	@Autowired
	private ClienteRepository repository;

	@Autowired
	private PessoaRepository pessoaRepository;

	public Cliente buscaPorId(Long id) {
		Optional<Cliente> obj = repository.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFundExeption("Objeto não encontrado id " + id));
	}

	public List<Cliente> buscaTodos() {

		return repository.findAll();
	}

	/*
	 * metodo que me retorna a instância do ClienteRepository com isso pode ter
	 * acesso aos metodos do jpa de forma direta
	 */
	public ClienteRepository getRepository() {
		return repository;
	}

	public Cliente created(ClienteDTO ClienteDTO) {
		ClienteDTO.setId(null);
		validaPorCpfEmail(ClienteDTO);
		Cliente newObj = new Cliente(ClienteDTO);
		return repository.save(newObj);
	}

	private void validaPorCpfEmail(ClienteDTO ClienteDTO) {
		Optional<Pessoa> obj = pessoaRepository.findBycpf(ClienteDTO.getCpf());
		if (obj.isPresent() && obj.get().getId() != ClienteDTO.getId()) {
			throw new DataIntegrityViolationException("Cpf já cadastrado no Sistema!!");
		}

		obj = pessoaRepository.findByemail(ClienteDTO.getEmail());

		if (obj.isPresent() && obj.get().getId() != ClienteDTO.getId()) {
			throw new DataIntegrityViolationException("Email já cadastrado no Sistema!!");
		}

	}

	public Cliente update(Long id, ClienteDTO ObjDto) {

		ObjDto.setId(id);
		Cliente oldObj = buscaPorId(id);
		validaPorCpfEmail(ObjDto);
		oldObj = new Cliente(ObjDto);

		return repository.save(oldObj);
	}

	public void Deletar(Long id) {
		Cliente oldtec = buscaPorId(id);

		if (oldtec.getChamados().size() > 0) {
			throw new DataIntegrityViolationException("Cliente Possui ordem de serviço e não pode ser Apagado !!");
		} else {
			repository.deleteById(id);
		}

	}

}
