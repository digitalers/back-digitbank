package ar.digitalers.back_digibank.repos;

import ar.digitalers.back_digibank.domain.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CuentaRepository extends JpaRepository<Cuenta, Long> {

    boolean existsByNumeroCuentaIgnoreCase(String numeroCuenta);

}
