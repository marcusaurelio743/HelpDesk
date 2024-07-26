package br.comHelpDesk.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.comHelpDesk.domain.Chamado;
import br.comHelpDesk.domain.Cliente;
import br.comHelpDesk.domain.Tecnico;
import br.comHelpDesk.domain.enums.Prioridade;
import br.comHelpDesk.domain.enums.Status;
import br.comHelpDesk.dtos.ChamadoDTO;
import br.comHelpDesk.repositores.ChamadoRepository;
import br.comHelpDesk.services.exeptions.ObjectNotFundExeption;

@Service
public class ChamadoService {
	@Autowired
	private ChamadoRepository repository;
	@Autowired
	private TecnicoService tecnicoService;
	@Autowired
	private ClienteService clienteService;
	
	
	public Chamado buscaPorId(Long id) {
		Optional<Chamado> obj = repository.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFundExeption("Objeto não encontrado id: "+id));
	}

	public List<Chamado> buscaTodos() {
		
		return repository.findAll();
	}

	public Chamado created(@Valid ChamadoDTO chamadoDTO) {
		
		return repository.save(newChamado(chamadoDTO));
	}
	public Chamado atualizar(Long id, @Valid ChamadoDTO chmDto) {
		chmDto.setId(id);
		
		Chamado oldChamado = buscaPorId(id);
		oldChamado= newChamado(chmDto);
		
		
		return repository.save(oldChamado);
	}
	
	private Chamado newChamado(ChamadoDTO chamadoDTO) {
		Cliente cliente = clienteService.buscaPorId(chamadoDTO.getCliente());
		Tecnico tecnico = tecnicoService.buscaPorId(chamadoDTO.getTecnico());
		
		Chamado chamado = new Chamado();
		
		if(chamadoDTO.getId() != null) {
			chamado.setId(chamadoDTO.getId());
		}
		if(chamadoDTO.getStatus().equals(2)) {
			chamado.setDataFechamento(LocalDate.now());
			
		}
		
		chamado.setTecnico(tecnico);
		chamado.setCliente(cliente);
		chamado.setTitulo(chamadoDTO.getTitulo());
		chamado.setObservacao(chamadoDTO.getObservacao());
		chamado.setStatus(Status.toEnum(chamadoDTO.getStatus()));
		chamado.setPrioridade(Prioridade.toEnum(chamadoDTO.getPrioridade()));
		return chamado;
		
	}

	

}
