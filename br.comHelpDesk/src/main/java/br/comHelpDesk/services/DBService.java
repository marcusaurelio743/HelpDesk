package br.comHelpDesk.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.comHelpDesk.domain.Chamado;
import br.comHelpDesk.domain.Cliente;
import br.comHelpDesk.domain.Tecnico;
import br.comHelpDesk.domain.enums.Perfil;
import br.comHelpDesk.domain.enums.Prioridade;
import br.comHelpDesk.domain.enums.Status;
import br.comHelpDesk.repositores.ChamadoRepository;
import br.comHelpDesk.repositores.ClienteRepository;
import br.comHelpDesk.repositores.TecnicoRepository;

@Service
public class DBService {
	@Autowired
	private TecnicoRepository tecnicoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private ChamadoRepository chamadoRepository;
	
	
	public void instanciaDB() {
		Tecnico t1 = new Tecnico(null, "Marcus Aurelio", "63826346033","marcus@email.com" , "123");
		t1.addPerfil(Perfil.ADMIN);
		Tecnico tec2 = new Tecnico(null, "Satoro Gojo", "123456789", "gojo@email.com", "123");
		
		Cliente cli1 = new Cliente(null, "Jose", "89794571032", "jose@email.com", "123");
		Cliente cli2 = new Cliente(null, "Maria", "987654321", "maria@email.com", "123");
		
		Chamado chm = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "chamado 01", "Primeiro chamado", cli1, t1);
		Chamado chm2 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "chamado2", "equipamento recebido em analise de defeito", cli2, tec2);
		clienteRepository.saveAll(Arrays.asList(cli1,cli2));
		tecnicoRepository.saveAll(Arrays.asList(t1,tec2));
		chamadoRepository.saveAll(Arrays.asList(chm,chm2));
	}

}
