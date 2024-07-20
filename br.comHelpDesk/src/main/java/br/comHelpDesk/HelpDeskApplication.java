package br.comHelpDesk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.comHelpDesk.domain.Chamado;
import br.comHelpDesk.domain.Cliente;
import br.comHelpDesk.domain.Tecnico;
import br.comHelpDesk.domain.enums.Perfil;
import br.comHelpDesk.domain.enums.Prioridade;
import br.comHelpDesk.domain.enums.Status;
import br.comHelpDesk.repositores.ChamadoRepository;
import br.comHelpDesk.repositores.ClienteRepository;
import br.comHelpDesk.repositores.TecnicoRepository;

@SpringBootApplication
public class HelpDeskApplication implements CommandLineRunner {
	
	@Autowired
	private TecnicoRepository tecnicoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private ChamadoRepository chamadoRepository;
	public static void main(String[] args) {
		SpringApplication.run(HelpDeskApplication.class,args);
	}

	@Override
	public void run(String... args) throws Exception {
		Tecnico t1 = new Tecnico(null, "Marcus Aurelio", "63826346033","marcus@email.com" , "123");
		t1.addPerfil(Perfil.ADMIN);
		
		Cliente cli1 = new Cliente(null, "Jose", "89794571032", "jose@email.com", "123");
		
		Chamado chm = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "chamado 01", "Primeiro chamado", cli1, t1);
		clienteRepository.save(cli1);
		tecnicoRepository.save(t1);
		chamadoRepository.save(chm);
		
		
	}

}
