package com.NashDev.myFitness.repository;

import com.NashDev.myFitness.Domain.Pessoa;
import com.NashDev.myFitness.service.exeption.BusinessExeption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    @Query("select pessoa "+ "from Pessoa pessoa "+"where pessoa.cpf = :cpf")
    Pessoa getByCpf(@Param("cpf") String cpf);
}
