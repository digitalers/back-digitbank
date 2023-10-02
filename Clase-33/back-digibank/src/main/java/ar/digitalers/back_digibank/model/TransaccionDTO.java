package ar.digitalers.back_digibank.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class TransaccionDTO {

    private Long id;

    @NotNull
    private LocalDateTime fechaTransaccion;

    @NotNull
    private TipoTransaccion tipoTransaccion;

    @NotNull
    private Double monto;

    @NotNull
    @Size(max = 255)
    private String cuentaOrigen;

    @NotNull
    @Size(max = 255)
    private String cuentaDestino;

    private Long cuentaOrigenId;

    private Long cuentaDestinoId;

}
