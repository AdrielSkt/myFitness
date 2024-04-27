package com.NashDev.myFitness.service.dto;

import com.NashDev.myFitness.Enums.Sexo;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class PessoaDto {


    private Long id;
    private String cpf;
    private String nome;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate datNascimento;
    private Sexo sexo;
    private double altura;
    private double peso;


    public double calculaPesoIdeal(){
        if(this.sexo.equals(Sexo.F)){
            return ((62.1*this.altura) -44.7);
        }
        return ((72.7*this.altura) -58);
    }
}
