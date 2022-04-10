package com.santander.banco811.controller;

import com.santander.banco811.dto.mappers.request.UsuarioRequestMapper;
import com.santander.banco811.dto.mappers.response.UsuarioResponseMapper;
import com.santander.banco811.dto.request.UsuarioRequest;
import com.santander.banco811.dto.response.UsuarioResponse;
import com.santander.banco811.model.Usuario;
import com.santander.banco811.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final UsuarioResponseMapper usuarioResponseMapper = UsuarioResponseMapper.INSTANCE;
    private final UsuarioRequestMapper usuarioRequestMapper = UsuarioRequestMapper.INSTANCE;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioResponse create(@RequestBody UsuarioRequest usuarioRequest) {
        Usuario usuario = usuarioRequestMapper.toDomain(usuarioRequest);
        usuarioService.save(usuario);
        return usuarioResponseMapper.toResponse(usuario);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UsuarioResponse findById(@PathVariable Integer id) {
        return usuarioService.findById(id);
    }

    @GetMapping("/searchNameCpf")
    @ResponseStatus(HttpStatus.OK)
    public List<UsuarioResponse> findByCpfAndNome(
            @RequestParam String cpf,
            @RequestParam String nome
    ) {
        return usuarioService.findByIdAndCpf(cpf, nome);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<UsuarioResponse> getAll(
            @RequestParam int pageNumber,
            @RequestParam int pageSize
    ) {
        return usuarioService.getAll(pageNumber, pageSize);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioResponse update(@PathVariable Integer id, @RequestBody UsuarioRequest usuarioRequest) {
        Usuario usuario = usuarioRequestMapper.toDomain(usuarioRequest);
        return usuarioService.update(id, usuario);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        usuarioService.delete(id);
    }
}
