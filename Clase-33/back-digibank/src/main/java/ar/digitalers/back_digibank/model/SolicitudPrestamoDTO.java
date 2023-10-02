package ar.digitalers.back_digibank.model;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class SolicitudPrestamoDTO {

    private Long id;

    @NotNull
    private LocalDateTime fechaSolicitud;

    @NotNull
    private Double montoSolicitado;

    @NotNull
    private EstadoSolicitudPrestamo estadoSolicitudPrestamo;

    private Long cliente;

}
