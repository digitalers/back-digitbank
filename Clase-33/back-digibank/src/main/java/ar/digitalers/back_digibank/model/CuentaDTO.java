package ar.digitalers.back_digibank.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CuentaDTO {

    private Long id;

    @NotNull
    @Size(max = 255)
    private String numeroCuenta;

    @NotNull
    private Double saldo;

    @NotNull
    private TipoCuenta tipoCuenta;

    private EstadoCuenta estadoCuenta;

    private Long cliente;

}
