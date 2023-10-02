package ar.digitalers.back_digibank.domain;

import ar.digitalers.back_digibank.model.EstadoCuenta;
import ar.digitalers.back_digibank.model.TipoCuenta;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Cuenta {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String numeroCuenta;

    @Column(nullable = false)
    private Double saldo;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoCuenta tipoCuenta;

    @Column
    @Enumerated(EnumType.STRING)
    private EstadoCuenta estadoCuenta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToMany(mappedBy = "cuentaOrigenId")
    private Set<Transaccion> transacciones;

}
