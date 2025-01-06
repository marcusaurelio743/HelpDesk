package br.com.helpdesk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.helpdesk.domain.Tecnico;

public interface TecnicoRepository extends JpaRepository<Tecnico, Long>{
	
}
