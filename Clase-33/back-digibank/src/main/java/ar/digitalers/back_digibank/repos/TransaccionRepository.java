package ar.digitalers.back_digibank.repos;

import ar.digitalers.back_digibank.domain.Transaccion;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TransaccionRepository extends JpaRepository<Transaccion, Long> {
}
