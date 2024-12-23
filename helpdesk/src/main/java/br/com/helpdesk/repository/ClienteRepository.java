package br.com.helpdesk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.helpdesk.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

}
