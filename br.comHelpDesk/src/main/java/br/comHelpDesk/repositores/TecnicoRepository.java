package br.comHelpDesk.repositores;

import org.springframework.data.jpa.repository.JpaRepository;

import br.comHelpDesk.domain.Tecnico;

public interface TecnicoRepository extends JpaRepository<Tecnico, Long> {

}
