package com.santander.banco811.services.impl;

import com.santander.banco811.dto.mappers.request.ContaRequestMapper;
import com.santander.banco811.dto.mappers.response.ContaResponseMapper;
import com.santander.banco811.dto.request.ContaRequest;
import com.santander.banco811.dto.response.ContaResponse;
import com.santander.banco811.exceptions.AccountNotFoundException;
import com.santander.banco811.exceptions.UserNotFoundException;
import com.santander.banco811.model.Conta;
import com.santander.banco811.model.TipoConta;
import com.santander.banco811.model.Usuario;
import com.santander.banco811.projection.ContaView;
import com.santander.banco811.repositories.ContaRepository;
import com.santander.banco811.repositories.UsuarioRepository;
import com.santander.banco811.services.ContaService;
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
public class ContaServiceImpl implements ContaService {

    private final ContaRepository contaRepository;
    private final UsuarioRepository usuarioRepository;
    private final ContaResponseMapper contaResponseMapper = ContaResponseMapper.INSTANCE;
    private final ContaRequestMapper contaRequestMapper = ContaRequestMapper.INSTANCE;

    @Override
    public Conta createConta(Integer id, ContaRequest contaRequest) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> UserNotFoundException.builder().build());
        Conta conta = contaRequestMapper.toDomain(contaRequest, usuario);
        return contaRepository.save(conta);
    }

    @Override
    public ContaResponse findById(Integer id) {
        Conta conta = contaRepository.findById(id).orElseThrow(() -> AccountNotFoundException.builder().build());
        return contaResponseMapper.toResponse(conta);
    }

    @Override
    public List<ContaView> getAllViewByTipoConta(TipoConta tipoConta) {
        return contaRepository.findAllByTipoConta(tipoConta);
    }

    @Override
    public Page<ContaResponse> getallContas(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, Sort.Direction.ASC, "id");
        Page<Conta> foundContas = contaRepository.findAll(pageRequest);
        return new PageImpl<ContaResponse>(foundContas.stream().map(contaResponseMapper::toResponse).collect(Collectors.toList()));
    }


    @Override
    public Conta updateConta(Integer id, ContaRequest contaRequest) {
        Conta contaEncontrada = contaRepository.findById(id).orElseThrow(() -> AccountNotFoundException.builder().build());
        Conta contaAtualizada = contaRequestMapper.toDomainWithoutUser(contaRequest);
        BeanUtils.copyProperties(contaAtualizada, contaEncontrada, "id", "dataCriacao", "usuario");
        return contaRepository.save(contaEncontrada);
    }


    @Override
    public void deleteContaById(Integer id) {
        if (contaRepository.existsById(id)) {
            contaRepository.deleteById(id);
        } else throw AccountNotFoundException.builder().build();
    }
}
