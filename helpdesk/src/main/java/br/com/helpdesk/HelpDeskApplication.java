package br.com.helpdesk;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.helpdesk.domain.Chamado;
import br.com.helpdesk.domain.Cliente;
import br.com.helpdesk.domain.Tecnico;
import br.com.helpdesk.domain.enums.Perfil;
import br.com.helpdesk.domain.enums.Prioridade;
import br.com.helpdesk.domain.enums.Status;
import br.com.helpdesk.repository.ChamadoRepository;
import br.com.helpdesk.repository.ClienteRepository;
import br.com.helpdesk.repository.TecnicoRepository;

@SpringBootApplication
public class HelpDeskApplication  implements CommandLineRunner{
	@Autowired
	TecnicoRepository tecnicoRepository;
	@Autowired
	ClienteRepository clienteRepository;
	@Autowired
	ChamadoRepository chamadoRepository;

	public static void main(String[] args)  {
		SpringApplication.run(HelpDeskApplication.class,args ); 

	}

	@Override
	public void run(String... args) throws Exception {
		Tecnico t1 = new Tecnico(null, "Jose", "73823694049", "jose@email.com", "1234556");
		Cliente c1 = new Cliente(null, "Marcus Aurélio", "78495900076", "Marcus@email.com", "123456");
		t1.AddPerfis(Perfil.TECNICO);
		Chamado ch1 = new Chamado(null, Prioridade.MEDIA, Status.ABERTO, "Reparo em PC", "Reparo na placa mae", t1, c1);
		clienteRepository.saveAll(Arrays.asList(c1));
		tecnicoRepository.saveAll(Arrays.asList(t1));
		chamadoRepository.saveAll(Arrays.asList(ch1));
	}

}
