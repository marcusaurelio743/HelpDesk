package br.comHelpDesk.repositores;

import org.springframework.data.jpa.repository.JpaRepository;

import br.comHelpDesk.domain.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}
