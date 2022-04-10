package com.santander.banco811.services;

import com.santander.banco811.dto.request.ContaRequest;
import com.santander.banco811.dto.response.ContaResponse;
import com.santander.banco811.model.Conta;
import com.santander.banco811.model.TipoConta;
import com.santander.banco811.projection.ContaView;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ContaService {
    List<ContaView> getAllViewByTipoConta(TipoConta tipoConta);

    Conta createConta(Integer id, ContaRequest contaRequest);

    ContaResponse findById(Integer id);

    Page<ContaResponse> getallContas(int pageNumber, int pageSize);

    Conta updateConta(Integer id, ContaRequest contaRequest);

    void deleteContaById(Integer id);
}
