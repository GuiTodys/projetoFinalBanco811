package com.santander.banco811.projection;

import com.santander.banco811.model.TipoConta;
import org.springframework.beans.factory.annotation.Value;

public interface ContaView {
    UsuarioView getUsuario();

    @Value("#{target.numero + '-' + target.agencia}")
    String getNumeroAgencia();

    Integer getSaldo();

    TipoConta getTipoConta();
}
