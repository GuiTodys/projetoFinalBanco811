package com.santander.banco811.services.impl;

import com.santander.banco811.dto.mappers.request.UsuarioRequestMapper;
import com.santander.banco811.dto.mappers.response.UsuarioResponseMapper;
import com.santander.banco811.dto.response.UsuarioResponse;
import com.santander.banco811.exceptions.UserNotFoundException;
import com.santander.banco811.model.Usuario;
import com.santander.banco811.repositories.UsuarioRepository;
import com.santander.banco811.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioResponseMapper usuarioResponseMapper = UsuarioResponseMapper.INSTANCE;
    private final UsuarioRequestMapper usuarioRequestMapper = UsuarioRequestMapper.INSTANCE;

    @Override
    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public UsuarioResponse findById(Integer id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> UserNotFoundException.builder().build());
        return usuarioResponseMapper.toResponse(usuario);
    }

    @Override
    public List<UsuarioResponse> findByIdAndCpf(String cpf, String nome) {
        List<Usuario> foundUser = usuarioRepository.findByNomeAndCpf(cpf, nome);
        if (foundUser.isEmpty()) throw UserNotFoundException.builder().build();
        return foundUser.stream().map(usuarioResponseMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public Page<UsuarioResponse> getAll(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, Sort.Direction.ASC, "id");
        Page<Usuario> foundUsuarios = usuarioRepository.findAll(pageRequest);
        return new PageImpl<UsuarioResponse>(foundUsuarios.stream().map(usuarioResponseMapper::toResponse).collect(Collectors.toList()));
    }

    @Override
    public UsuarioResponse update(Integer id, Usuario usuarioAtualizado) {
        Usuario usuarioEncontrado = usuarioRepository.findById(id).orElseThrow(() -> UserNotFoundException.builder().build());
        BeanUtils.copyProperties(usuarioAtualizado, usuarioEncontrado, "id", "dataCriacao");
        return usuarioResponseMapper.toResponse(usuarioRepository.save(usuarioEncontrado));
    }

    @Override
    public void delete(Integer id) {
        usuarioRepository.deleteById(id);
    }
}
