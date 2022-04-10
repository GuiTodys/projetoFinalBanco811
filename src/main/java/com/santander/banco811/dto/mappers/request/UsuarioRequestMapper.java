package com.santander.banco811.dto.mappers.request;

import com.santander.banco811.dto.request.UsuarioRequest;
import com.santander.banco811.model.Usuario;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UsuarioRequestMapper {
    UsuarioRequestMapper INSTANCE = Mappers.getMapper(UsuarioRequestMapper.class);

    Usuario toDomain(UsuarioRequest usuarioRequest);

    @InheritInverseConfiguration
    UsuarioRequest toRequest(Usuario usuario);
}
