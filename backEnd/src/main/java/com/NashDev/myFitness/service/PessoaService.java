package com.NashDev.myFitness.service;

import com.NashDev.myFitness.repository.PessoaRepository;
import com.NashDev.myFitness.service.dto.PessoaDto;
import com.NashDev.myFitness.service.exeption.BusinessExeption;
import com.NashDev.myFitness.service.mapper.PessoaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PessoaService {

    @Autowired
    PessoaRepository pessoaRepository;

    @Autowired
    PessoaMapper pessoaMapper;
    public PessoaDto getbyCpf(String cpf){
            PessoaDto pessoa = pessoaMapper.toDto(pessoaRepository.getByCpf(cpf));
            if(pessoa == null){
                throw new BusinessExeption("Pessoa nao encontrada");
            }
            return pessoa;
    }


    public PessoaDto create(PessoaDto pessoa){
        try{
            return pessoaMapper.toDto(pessoaRepository.save(pessoaMapper.toEntity(pessoa)));
        }catch (Exception e){
            throw new BusinessExeption("Erro ao salvar pessoa");
        }

    }

    public PessoaDto update(PessoaDto pessoa){
        PessoaDto pessoaForUpdate = getbyCpf(pessoa.getCpf());
        pessoa.setId(pessoaForUpdate.getId());
        try{
            return pessoaMapper.toDto(pessoaRepository.save(pessoaMapper.toEntity(pessoa)));
        }catch (Exception e){
            throw new BusinessExeption("Erro ao atualizar pessoa");
        }
    }

    public void deleteByCpf(String cpf){
        PessoaDto pessoa = getbyCpf(cpf);
        try {
            pessoaRepository.deleteById(pessoa.getId());
        }catch (Exception e){
            throw new BusinessExeption("Erro ao excluir pessoa");
        }
    }

    public Double calculaPesoIdeal(PessoaDto pessoa){
        return pessoa.calculaPesoIdeal();
    }
}
