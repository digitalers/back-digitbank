package ar.digitalers.back_digibank.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class TarjetaDTO {

    private Long id;

    @NotNull
    @Size(max = 255)
    private String numeroTarjeta;

    @NotNull
    private TipoTarjeta tipoTarjeta;

    @NotNull
    private LocalDateTime fechaVencimiento;

    @NotNull
    private Double saldoDisponible;

    private Long cliente;

}
