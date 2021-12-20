package br.com.personal.fabioramires.bank.controller;

import java.util.List;

import br.com.personal.fabioramires.bank.model.Movimentacao;
import br.com.personal.fabioramires.bank.service.MovimentacaoService;
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
@RequestMapping("movimentacao")
@SecurityRequirement(name = "fabioramiresbank")
public class MovimentacaoController {
    @Autowired
    private MovimentacaoService movimentacaoService;


    @RequestMapping(method=RequestMethod.POST, path="salvar" )
    @Operation(summary = "Salva uma Movimentação", security = @SecurityRequirement(name = "fabioramiresbank"))
    public ResponseEntity<?> depositar(@RequestBody Movimentacao trans){
        Movimentacao movimentacao = this.movimentacaoService.salvar(trans);
        return new ResponseEntity<>(movimentacao, HttpStatus.OK);
    }

    @RequestMapping(method=RequestMethod.DELETE, path="deletar/{id}" )
    @Operation(summary = "Exclui uma Movimentação", security = @SecurityRequirement(name = "fabioramiresbank"))
    public ResponseEntity<?> saldo( @PathVariable Long id){

        this.movimentacaoService.delete(id);

        return new ResponseEntity<>( HttpStatus.OK);
    }

    @RequestMapping(method=RequestMethod.GET, path="listar" )
    @Operation(summary = "Retorna Lista de Movimentações", security = @SecurityRequirement(name = "fabioramiresbank"))
    public ResponseEntity<?> listar(){

        List<Movimentacao> transacoes = this.movimentacaoService.listar();
        return new ResponseEntity<>(transacoes, HttpStatus.OK);
    }

    @RequestMapping(method=RequestMethod.GET, path="buscar/{id}"  )
    @Operation(summary = "Retorna uma Movimentação Específica", security = @SecurityRequirement(name = "fabioramiresbank"))
    public ResponseEntity<?> lisbuscarPorId(@PathVariable Long id){
        movimentacaoService.buscarPeloId(id);
        return new ResponseEntity<>( HttpStatus.OK);
    }
}