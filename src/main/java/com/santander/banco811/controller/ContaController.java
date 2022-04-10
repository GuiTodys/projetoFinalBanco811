package com.santander.banco811.controller;

import com.santander.banco811.dto.mappers.request.ContaRequestMapper;
import com.santander.banco811.dto.mappers.response.ContaResponseMapper;
import com.santander.banco811.dto.request.ContaRequest;
import com.santander.banco811.dto.response.ContaResponse;
import com.santander.banco811.model.TipoConta;
import com.santander.banco811.projection.ContaView;
import com.santander.banco811.services.ContaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/contas")
@RequiredArgsConstructor
public class ContaController {

    private final ContaRequestMapper contaRequestMapper = ContaRequestMapper.INSTANCE;
    private final ContaResponseMapper contaResponseMapper = ContaResponseMapper.INSTANCE;
    private final ContaService contaService;

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ContaResponse create(@PathVariable Integer id, @RequestBody @Valid ContaRequest contaRequest) {
        return contaResponseMapper.toResponse(contaService.createConta(id, contaRequest));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ContaResponse findById(@PathVariable Integer id) {
        return contaService.findById(id);
    }

    @GetMapping("/view")
    @ResponseStatus(HttpStatus.OK)
    public List<ContaView> getAllContaViewByTipoConta(
            @RequestParam TipoConta tipoConta
    ) {
        return contaService.getAllViewByTipoConta(tipoConta);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<ContaResponse> getAll(
            @RequestParam int pageNumber,
            @RequestParam int pageSize) {
        return contaService.getallContas(pageNumber, pageSize);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)

    public ContaResponse update(
            @PathVariable Integer id,
            @RequestBody @Valid ContaRequest contaRequest) {
        return contaResponseMapper.toResponse(contaService.updateConta(id, contaRequest));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Integer id) {
        contaService.deleteContaById(id);
    }


}
