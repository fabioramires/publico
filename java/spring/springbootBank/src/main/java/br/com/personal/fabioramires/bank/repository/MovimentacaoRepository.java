package br.com.personal.fabioramires.bank.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.personal.fabioramires.bank.model.Conta;
import br.com.personal.fabioramires.bank.model.Movimentacao;

@Repository
@Transactional(readOnly = false)
public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long> {

    List<Movimentacao> findByContaIn(Collection<Conta> conta);
}