package ar.digitalers.back_digibank.domain;

import ar.digitalers.back_digibank.model.TipoTransaccion;
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
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Transaccion {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime fechaTransaccion;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoTransaccion tipoTransaccion;

    @Column(nullable = false)
    private Double monto;

    @Column(nullable = false)
    private String cuentaOrigen;

    @Column(nullable = false)
    private String cuentaDestino;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cuenta_origen_id_id")
    private Cuenta cuentaOrigenId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cuenta_destino_id_id")
    private Cuenta cuentaDestinoId;

}
