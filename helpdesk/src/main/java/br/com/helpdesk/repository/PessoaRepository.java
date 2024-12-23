package br.com.helpdesk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.helpdesk.domain.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>{

}
