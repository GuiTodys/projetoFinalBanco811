package com.santander.banco811.dto.mappers.request;

import com.santander.banco811.dto.request.ContaRequest;
import com.santander.banco811.model.Conta;
import com.santander.banco811.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ContaRequestMapper {
    ContaRequestMapper INSTANCE = Mappers.getMapper(ContaRequestMapper.class);

    @Mapping(source = "usuarioConta", target = "usuario")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dataCriacao", ignore = true)
    @Mapping(target = "dataAtualizacao", ignore = true)
    Conta toDomain(ContaRequest contaRequest, Usuario usuarioConta);

    Conta toDomainWithoutUser(ContaRequest contaRequest);

    ContaRequest toRequest(Conta conta);
}
