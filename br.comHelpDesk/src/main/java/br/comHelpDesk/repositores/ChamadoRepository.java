package br.comHelpDesk.repositores;

import org.springframework.data.jpa.repository.JpaRepository;

import br.comHelpDesk.domain.Chamado;

public interface ChamadoRepository extends JpaRepository<Chamado, Long> {

}
