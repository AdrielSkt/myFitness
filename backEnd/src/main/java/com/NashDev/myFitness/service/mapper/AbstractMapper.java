package com.NashDev.myFitness.service.mapper;

import java.util.List;

public interface AbstractMapper<D,E>{

    E toEntity(D dto);

    D toDto(E entity);

    List<E> toEntity(List<D> DtoList);

    List<D> toDto(List<E> EntityList);
}
