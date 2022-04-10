package com.santander.banco811.dto.request;

import com.santander.banco811.model.TipoConta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContaRequest {
    @Min(value = 10000, message = "O número da conta deve conter 5 dígitos!")
    private Integer numero;
    @Min(value = 1000, message = "Agência deve conter 4 dígitos")
    private Integer agencia;
    @NotNull
    private BigDecimal saldo;
    @NotNull
    private TipoConta tipoConta;
}
