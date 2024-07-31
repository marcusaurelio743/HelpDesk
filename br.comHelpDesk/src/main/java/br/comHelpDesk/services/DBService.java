package br.comHelpDesk.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	
	public void instanciaDB() {
		Tecnico t1 = new Tecnico(null, "Marcus Aurelio", "63826346033","marcus@email.com" , encoder.encode("123"));
		t1.addPerfil(Perfil.ADMIN);
		Tecnico tec2 = new Tecnico(null, "Satoro Gojo", "38442269070", "gojo@email.com", encoder.encode("123"));
		Tecnico tec3 = new Tecnico(null, "Satoro Gojo", "86500335040", "gojo2@email.com", encoder.encode("123"));
		Tecnico tec4 = new Tecnico(null, "Satoro Gojo", "41246390078", "gojo3@email.com", encoder.encode("123"));
		Tecnico tec5 = new Tecnico(null, "Satoro Gojo", "03124262085", "goj4o@email.com", encoder.encode("123"));
		
		Cliente cli1 = new Cliente(null, "Jose", "00215115007", "jose@email.com",encoder.encode("123"));
		Cliente cli2 = new Cliente(null, "Maria", "28697557089", "maria1@email.com", encoder.encode("123"));
		Cliente cli3 = new Cliente(null, "Maria1", "76293597052", "maria2@email.com", encoder.encode("123"));
		Cliente cli4 = new Cliente(null, "Maria2", "20448366029", "maria3@email.com", encoder.encode("123"));
		Cliente cli5 = new Cliente(null, "Maria3", "47434688092", "maria4@email.com", encoder.encode("123"));
		
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
