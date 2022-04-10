package com.santander.banco811.dto.mappers.response;

import com.santander.banco811.dto.response.ContaResponse;
import com.santander.banco811.model.Conta;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {UsuarioResponseMapper.class})
public interface ContaResponseMapper {
    ContaResponseMapper INSTANCE = Mappers.getMapper(ContaResponseMapper.class);

    Conta toDomain(ContaResponse contaResponse);

    ContaResponse toResponse(Conta conta);
}
