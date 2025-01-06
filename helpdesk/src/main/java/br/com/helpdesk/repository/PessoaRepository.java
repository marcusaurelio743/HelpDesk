package br.com.helpdesk.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.helpdesk.domain.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>{
	Optional<Pessoa> findBycpf(String cpf);
	Optional<Pessoa> findByemail(String email);
}
