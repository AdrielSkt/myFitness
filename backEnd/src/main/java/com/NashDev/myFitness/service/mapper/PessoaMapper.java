package com.NashDev.myFitness.service.mapper;

import com.NashDev.myFitness.Domain.Pessoa;
import com.NashDev.myFitness.service.dto.PessoaDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {PessoaMapper.class})
public interface PessoaMapper extends AbstractMapper<PessoaDto, Pessoa> {
}
