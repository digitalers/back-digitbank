package ar.digitalers.back_digibank.repos;

import ar.digitalers.back_digibank.domain.Tarjeta;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TarjetaRepository extends JpaRepository<Tarjeta, Long> {
}
