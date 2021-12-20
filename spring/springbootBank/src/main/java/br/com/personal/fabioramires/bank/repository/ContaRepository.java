package br.com.personal.fabioramires.bank.repository;

import br.com.personal.fabioramires.bank.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = false)
public interface ContaRepository extends JpaRepository<Conta, Long>{

    Conta findByIdConta(Long id);

    @Modifying
    @Query("update Conta c set c.saldo = c.saldo + ?1 where c.idConta = ?2")
    void setAumentaSaldo(double valor, Long id);

    @Modifying
    @Query("update Conta c set c.saldo = c.saldo - ?1 where c.idConta = ?2")
    void setDiminuiSaldo(double valor, Long id);

}