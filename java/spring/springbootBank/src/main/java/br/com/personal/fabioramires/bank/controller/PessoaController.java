package br.com.personal.fabioramires.bank.controller;

import java.util.List;

import br.com.personal.fabioramires.bank.model.Pessoa;
import br.com.personal.fabioramires.bank.service.PessoaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@Controller
@RestController
@RequestMapping("pessoa")
@SecurityRequirement(name = "fabioramiresbank")
public class PessoaController {
    @Autowired
    PessoaService pessoaService;

    @RequestMapping(method=RequestMethod.POST, path="salvar")
    @Operation(summary = "Salva uma Pessoa", security = @SecurityRequirement(name = "fabioramiresbank"))
    public ResponseEntity<?> salvar(@RequestBody Pessoa pessoa){
        Pessoa p = pessoaService.salvar(pessoa);
        return new ResponseEntity<>(p, HttpStatus.OK);
    }

    @RequestMapping(method=RequestMethod.GET, path="listar")
    @Operation(summary = "Retorna Lista de Pessoas", security = @SecurityRequirement(name = "fabioramiresbank"))
    public ResponseEntity<?> listar(){
        List<Pessoa> pessoas = pessoaService.listar();
        return new ResponseEntity<>(pessoas, HttpStatus.OK);
    }
}
