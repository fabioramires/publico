package br.com.personal.fabioramires.bank.repository;

import java.util.Optional;

import br.com.personal.fabioramires.bank.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    Optional<Pessoa> findByNome (String nome);
    Pessoa findByCpf(String cpf);
}