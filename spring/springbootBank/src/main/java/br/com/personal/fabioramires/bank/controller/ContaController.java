package br.com.personal.fabioramires.bank.controller;

import java.util.List;

import br.com.personal.fabioramires.bank.common.exception.ExceptionCustom;
import br.com.personal.fabioramires.bank.model.Conta;
import br.com.personal.fabioramires.bank.model.Movimentacao;
import br.com.personal.fabioramires.bank.service.ContaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
@RequestMapping("conta")
@SecurityRequirement(name = "fabioramiresbank")
public class ContaController {
    @Autowired
    private ContaService contaService;

    @RequestMapping(method=RequestMethod.POST, path="salvar" )
    @Operation(summary = "Salva uma Conta", security = @SecurityRequirement(name = "fabioramiresbank"))
    public ResponseEntity<?> salvar( @RequestBody Conta conta ){
        Conta c = this.contaService.salvar(conta);
        return new ResponseEntity<>(c, HttpStatus.OK);
    }

    @RequestMapping(method=RequestMethod.PUT, path= "depositar/{valor}/{id}")
    @Operation(summary = "Realiza Depósito na Conta Informada", security = @SecurityRequirement(name = "fabioramiresbank"))
    public ResponseEntity<?> depositar(@PathVariable double valor, @PathVariable Long id){

        this.contaService.deposita(valor, id);
        return new ResponseEntity<>( HttpStatus.OK);
    }

    @RequestMapping(method=RequestMethod.GET, path="saldo/{id}" )
    @Operation(summary = "Retorna Saldo da Conta Informada", security = @SecurityRequirement(name = "fabioramiresbank"))
    public ResponseEntity<?> saldo( @PathVariable Long id){

        Conta c = contaService.buscarSado(id);

        return new ResponseEntity<>(c.getSaldo(), HttpStatus.OK);
    }

    @RequestMapping(method=RequestMethod.PUT, path= "sacar/{valor}/{id}")
    @Operation(summary = "Realiza um Saque na Conta Informada", security = @SecurityRequirement(name = "fabioramiresbank"))
    public ResponseEntity<?> sacar(@PathVariable double valor, @PathVariable Long id){
        if (valor <= 0) {
            throw new ExceptionCustom("Valor deve Ser Maior que 0(Zero). Verifique!");
        }
        this.contaService.sacar(valor, id);
        return new ResponseEntity<>( HttpStatus.OK);
    }

    @RequestMapping(method=RequestMethod.PUT, path= "transferir/{valor}/{idContaOrigem}/{idContaDestino}")
    @Operation(summary = "Realiza uma Transferência entre Contas", security = @SecurityRequirement(name = "fabioramiresbank"))
    public ResponseEntity<?> transferir(@PathVariable double valor, @PathVariable Long idContaOrigem, @PathVariable Long idContaDestino){
        if (valor <= 0) {
            throw new ExceptionCustom("Valor deve Ser Maior que 0(Zero). Verifique!");
        }
        this.contaService.transferir(valor, idContaOrigem, idContaDestino);
        return new ResponseEntity<>( HttpStatus.OK);
    }

    @RequestMapping(method=RequestMethod.GET, path="listar" )
    @Operation(summary = "Retorna Lista de Contas Cadastradas", security = @SecurityRequirement(name = "fabioramiresbank"))
    public ResponseEntity<?> listar(){

        List<Conta> contas = contaService.listar();

        return new ResponseEntity<>(contas, HttpStatus.OK);
    }

    @RequestMapping(method=RequestMethod.GET, path="extratoConta/{id}" )
    @Operation(summary = "Busca Movimentação Registrada para a Conta Informada", security = @SecurityRequirement(name = "fabioramiresbank"))
    public ResponseEntity<?> extratoConta(@PathVariable Long id){
        List<Movimentacao> transacoes = contaService.extratoConta(id);

        return new ResponseEntity<>(transacoes,HttpStatus.OK);
    }

    @RequestMapping(method=RequestMethod.GET, path= "contaAtiva/{id}")
    @Operation(summary = "Verifica se Conta Informada está Ativa", security = @SecurityRequirement(name = "fabioramiresbank"))
    public ResponseEntity<?> verificarContaAtiva(@PathVariable Long id){

        Conta conta = contaService.verificarConta(id) ;
        if (conta.isFlagAtivo() == false) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(conta.getPessoa(),HttpStatus.OK);
    }
}
