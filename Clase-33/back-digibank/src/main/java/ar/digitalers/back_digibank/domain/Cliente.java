package ar.digitalers.back_digibank.domain;

import ar.digitalers.back_digibank.model.EstadoCuenta;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Cliente {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)   
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @Column(nullable = false)
    private String dni;

    @Column(nullable = false, unique = true)
    private String email;

    @Column
    private String telefono;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EstadoCuenta estadoCuenta;

    @OneToMany(mappedBy = "cliente")
    private Set<Cuenta> cuentas;

    @OneToMany(mappedBy = "cliente")
    private Set<SolicitudPrestamo> solicitudPrestamos;

    @OneToMany(mappedBy = "cliente")
    private Set<Tarjeta> tarjetas;

}
