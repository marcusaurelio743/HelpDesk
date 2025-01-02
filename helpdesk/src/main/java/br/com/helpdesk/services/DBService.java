package br.com.helpdesk.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.helpdesk.domain.Chamado;
import br.com.helpdesk.domain.Cliente;
import br.com.helpdesk.domain.Tecnico;
import br.com.helpdesk.domain.enums.Perfil;
import br.com.helpdesk.domain.enums.Prioridade;
import br.com.helpdesk.domain.enums.Status;
import br.com.helpdesk.repository.ChamadoRepository;
import br.com.helpdesk.repository.ClienteRepository;
import br.com.helpdesk.repository.TecnicoRepository;

@Service
public class DBService {
	
	@Autowired
	TecnicoRepository tecnicoRepository;
	@Autowired
	ClienteRepository clienteRepository;
	@Autowired
	ChamadoRepository chamadoRepository;
	
	public void instanciaDB() {
		Tecnico t1 = new Tecnico(null, "Jose", "73823694049", "jose@email.com", "1234556");
		Tecnico t2 = new Tecnico(null, "Maria", "00387686070", "maria@email.com", "1234556");
		Tecnico t3 = new Tecnico(null, "Mariana", "59506022003", "mariana@email.com", "1234556");
		t1.AddPerfis(Perfil.ADMIN);
		
		
		Cliente c1 = new Cliente(null, "Marcus Aurélio", "22768199010", "Marcus@email.com", "123456");
		Cliente c2 = new Cliente(null, "Paulo da Silva", "42564266045", "Paulo@email.com", "123456");
		Cliente c3 = new Cliente(null, "Marta", "70762613084", "Marta@email.com", "123456");
		
		
		
		Chamado ch1 = new Chamado(null, Prioridade.MEDIA, Status.ABERTO, "Reparo em PC", "Reparo na placa mae", t1, c1);
		Chamado ch2 = new Chamado(null, Prioridade.MEDIA, Status.ABERTO, "Reparo em PC", "formatação", t2, c2);
		Chamado ch3 = new Chamado(null, Prioridade.MEDIA, Status.ABERTO, "Reparo em PC", "Limpeza de componentes", t3, c3);
		
		
		clienteRepository.saveAll(Arrays.asList(c1,c2,c3));
		tecnicoRepository.saveAll(Arrays.asList(t1,t2,t3));
		chamadoRepository.saveAll(Arrays.asList(ch1,ch2,ch3));
	}

}
