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
		Cliente c1 = new Cliente(null, "Marcus Aurélio", "78495900076", "Marcus@email.com", "123456");
		t1.AddPerfis(Perfil.TECNICO);
		Chamado ch1 = new Chamado(null, Prioridade.MEDIA, Status.ABERTO, "Reparo em PC", "Reparo na placa mae", t1, c1);
		clienteRepository.saveAll(Arrays.asList(c1));
		tecnicoRepository.saveAll(Arrays.asList(t1));
		chamadoRepository.saveAll(Arrays.asList(ch1));
	}

}
