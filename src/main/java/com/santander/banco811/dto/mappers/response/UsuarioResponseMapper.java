package com.santander.banco811.dto.mappers.response;

import com.santander.banco811.dto.response.UsuarioResponse;
import com.santander.banco811.model.Usuario;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {ContaResponseMapper.class})
public interface UsuarioResponseMapper {
    UsuarioResponseMapper INSTANCE = Mappers.getMapper(UsuarioResponseMapper.class);

    Usuario toDomain(UsuarioResponse usuarioResponse);

    @InheritInverseConfiguration
    UsuarioResponse toResponse(Usuario usuario);
}
