package com.santander.banco811.services;

import com.santander.banco811.dto.response.UsuarioResponse;
import com.santander.banco811.model.Usuario;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UsuarioService {
    Usuario save(Usuario usuario);

    UsuarioResponse findById(Integer id);

    List<UsuarioResponse> findByIdAndCpf(String cpf, String nome);

    Page<UsuarioResponse> getAll(int pageNumber, int pageSize);

    UsuarioResponse update(Integer id, Usuario usuarioAtualizado);

    void delete(Integer id);
}
