package com.NashDev.myFitness.webRest;

import com.NashDev.myFitness.service.PessoaService;
import com.NashDev.myFitness.service.dto.PessoaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/pessoa")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class PessoaResource {

    @Autowired
    PessoaService pessoaService;


    @GetMapping(value = "/{cpf}")
    public ResponseEntity<PessoaDto> getByCpf(@PathVariable String cpf){
        PessoaDto response = pessoaService.getbyCpf(cpf);

        return ResponseEntity.ok().body(response);
    }

    @PostMapping(value = "/calcula_peso_ideal")
    public ResponseEntity<Double> calculaPesoIdeal(@RequestBody PessoaDto pessoa){
        double response = pessoaService.calculaPesoIdeal(pessoa);

        return ResponseEntity.ok().body(response);
    }

    @PostMapping
    public ResponseEntity<PessoaDto> create(@RequestBody PessoaDto pessoa){
        PessoaDto response = pessoaService.create(pessoa);
        return ResponseEntity.created(URI.create("api/user"+response.getCpf())).body(response);
    }

    @PutMapping
    public ResponseEntity<PessoaDto> update(@RequestBody PessoaDto pessoa){
        PessoaDto response = pessoaService.update(pessoa);
        return ResponseEntity.created(URI.create("api/user"+response.getCpf())).body(response);
    }

    @DeleteMapping(value = "/{cpf}")
    public ResponseEntity<Void> delete(@PathVariable String cpf){
        pessoaService.deleteByCpf(cpf);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
