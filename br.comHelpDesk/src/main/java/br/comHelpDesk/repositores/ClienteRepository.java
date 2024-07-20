package br.comHelpDesk.repositores;

import org.springframework.data.jpa.repository.JpaRepository;

import br.comHelpDesk.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
