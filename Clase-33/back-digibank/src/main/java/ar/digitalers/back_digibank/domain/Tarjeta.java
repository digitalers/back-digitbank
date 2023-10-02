package ar.digitalers.back_digibank.domain;

import ar.digitalers.back_digibank.model.TipoTarjeta;
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
public class Tarjeta {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String numeroTarjeta;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoTarjeta tipoTarjeta;

    @Column(nullable = false)
    private LocalDateTime fechaVencimiento;

    @Column(nullable = false)
    private Double saldoDisponible;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

}
