package ar.digitalers.back_digibank.repos;

import ar.digitalers.back_digibank.domain.Cliente;
import ar.digitalers.back_digibank.model.ClienteDTO;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    boolean existsByEmailIgnoreCase(String email);

    Optional<Cliente> findByEmail(String email);
}
