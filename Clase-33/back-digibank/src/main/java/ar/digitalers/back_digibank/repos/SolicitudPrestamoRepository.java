package ar.digitalers.back_digibank.repos;

import ar.digitalers.back_digibank.domain.SolicitudPrestamo;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SolicitudPrestamoRepository extends JpaRepository<SolicitudPrestamo, Long> {
}
