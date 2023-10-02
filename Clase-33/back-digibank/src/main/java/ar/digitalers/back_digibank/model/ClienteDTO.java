package ar.digitalers.back_digibank.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ClienteDTO {

    private Long id;

    @NotNull
    @Size(max = 255)
    private String nombre;

    @NotNull
    @Size(max = 255)
    private String apellido;

    @NotNull
    @Size(max = 255)
    private String dni;

    @NotNull
    @Size(max = 255)
    private String email;

    @Size(max = 255)
    private String telefono;

    @NotNull
    private EstadoCuenta estadoCuenta;

}
