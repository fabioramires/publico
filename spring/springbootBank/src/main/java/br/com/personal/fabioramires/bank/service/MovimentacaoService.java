package br.com.personal.fabioramires.bank.service;

import java.util.Collection;
import java.util.List;

import br.com.personal.fabioramires.bank.model.Conta;
import br.com.personal.fabioramires.bank.model.Movimentacao;
import br.com.personal.fabioramires.bank.repository.MovimentacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class MovimentacaoService {
    @Autowired
    private MovimentacaoRepository movimentacaoRepository;

    @Transactional
    public Movimentacao salvar(Movimentacao trans) {
        return movimentacaoRepository.save(trans);
    }

    public List<Movimentacao> listar() {
        return movimentacaoRepository.findAll();
    }

    @Transactional
    public void delete(Long id) {
        movimentacaoRepository.deleteById(id);
    }

    public void buscarPeloId(Long id) {
        movimentacaoRepository.findById(id);
    }

    public List<Movimentacao> buscarContas(Conta conta) {
        return movimentacaoRepository.findByContaIn((Collection<Conta>) conta);
    }
}
