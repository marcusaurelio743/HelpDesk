package br.com.helpdesk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.helpdesk.domain.Chamado;

public interface ChamadoRepository extends JpaRepository<Chamado, Long> {

}
