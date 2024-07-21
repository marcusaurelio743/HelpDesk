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
		Tecnico tec3 = new Tecnico(null, "Satoro Gojo", "123456344789", "gojo2@email.com", "123");
		Tecnico tec4 = new Tecnico(null, "Satoro Gojo", "123443456789", "gojo3@email.com", "123");
		Tecnico tec5 = new Tecnico(null, "Satoro Gojo", "1234543436789", "goj4o@email.com", "123");
		
		Cliente cli1 = new Cliente(null, "Jose", "89794571032", "jose@email.com", "123");
		Cliente cli2 = new Cliente(null, "Maria", "987654321", "maria1@email.com", "123");
		Cliente cli3 = new Cliente(null, "Maria1", "987654344521", "maria2@email.com", "123");
		Cliente cli4 = new Cliente(null, "Maria2", "9876454554321", "maria3@email.com", "123");
		Cliente cli5 = new Cliente(null, "Maria3", "987655454321", "maria4@email.com", "123");
		
		Chamado chm = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "chamado 01", "Primeiro chamado", cli1, t1);
		Chamado chm2 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "chamado2", "equipamento recebido em analise de defeito", cli2, tec2);
		Chamado chm3 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "chamado2", "equipamento recebido em analise de defeito", cli3, tec3);
		Chamado chm4 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "chamado2", "equipamento recebido em analise de defeito", cli4, tec4);
		Chamado chm5 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "chamado2", "equipamento recebido em analise de defeito", cli5, tec5);
		clienteRepository.saveAll(Arrays.asList(cli1,cli2,cli3,cli4,cli5));
		tecnicoRepository.saveAll(Arrays.asList(t1,tec2,tec3,tec4,tec5));
		chamadoRepository.saveAll(Arrays.asList(chm,chm2,chm3,chm4,chm5));
	}

}
