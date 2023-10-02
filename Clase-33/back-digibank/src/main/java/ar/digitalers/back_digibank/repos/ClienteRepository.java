package ar.digitalers.back_digibank.repos;

import ar.digitalers.back_digibank.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    boolean existsByEmailIgnoreCase(String email);

}
