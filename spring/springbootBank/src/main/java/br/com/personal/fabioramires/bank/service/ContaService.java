package br.com.personal.fabioramires.bank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.personal.fabioramires.bank.model.Conta;
import br.com.personal.fabioramires.bank.model.Movimentacao;
import br.com.personal.fabioramires.bank.repository.ContaRepository;

import javax.transaction.Transactional;

@Service
public class ContaService {
    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private MovimentacaoService movimentacaoService;

    @Transactional
    public Conta salvar(Conta conta) {
        return contaRepository.save(conta);
    }

    @Transactional
    public void deposita( double valor, Long id) {
        contaRepository.setAumentaSaldo(valor, id);
    }

    @Transactional
    public void sacar( double valor, Long id) {
        contaRepository.setDiminuiSaldo(valor, id);
    }

    @Transactional
    public void transferir( double valor, Long idContaOrigem, Long idContaDestino) {
        contaRepository.setDiminuiSaldo(valor, idContaOrigem);
        contaRepository.setAumentaSaldo(valor, idContaDestino);
    }

    public Conta buscarSado(Long id) {
        return contaRepository.findByIdConta(id);
    }

    public List<Conta> listar() {
        return contaRepository.findAll();
    }

    public Conta verificarConta(Long id) {
        return contaRepository.findByIdConta(id);
    }

    public List<Movimentacao> extratoConta(Long id) {
        Conta c = verificarConta(id);
        return movimentacaoService.buscarContas(c);
    }
}
