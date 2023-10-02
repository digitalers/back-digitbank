package ar.digitalers.back_digibank.service;

import ar.digitalers.back_digibank.domain.Cliente;
import ar.digitalers.back_digibank.domain.Cuenta;
import ar.digitalers.back_digibank.model.CuentaDTO;
import ar.digitalers.back_digibank.repos.ClienteRepository;
import ar.digitalers.back_digibank.repos.CuentaRepository;
import ar.digitalers.back_digibank.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class CuentaService {

    private final CuentaRepository cuentaRepository;
    private final ClienteRepository clienteRepository;

    public CuentaService(final CuentaRepository cuentaRepository,
            final ClienteRepository clienteRepository) {
        this.cuentaRepository = cuentaRepository;
        this.clienteRepository = clienteRepository;
    }

    public List<CuentaDTO> findAll() {
        final List<Cuenta> cuentas = cuentaRepository.findAll(Sort.by("id"));
        return cuentas.stream()
                .map(cuenta -> mapToDTO(cuenta, new CuentaDTO()))
                .toList();
    }

    public CuentaDTO get(final Long id) {
        return cuentaRepository.findById(id)
                .map(cuenta -> mapToDTO(cuenta, new CuentaDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final CuentaDTO cuentaDTO) {
        final Cuenta cuenta = new Cuenta();
        mapToEntity(cuentaDTO, cuenta);
        return cuentaRepository.save(cuenta).getId();
    }

    public void update(final Long id, final CuentaDTO cuentaDTO) {
        final Cuenta cuenta = cuentaRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(cuentaDTO, cuenta);
        cuentaRepository.save(cuenta);
    }

    public void delete(final Long id) {
        cuentaRepository.deleteById(id);
    }

    private CuentaDTO mapToDTO(final Cuenta cuenta, final CuentaDTO cuentaDTO) {
        cuentaDTO.setId(cuenta.getId());
        cuentaDTO.setNumeroCuenta(cuenta.getNumeroCuenta());
        cuentaDTO.setSaldo(cuenta.getSaldo());
        cuentaDTO.setTipoCuenta(cuenta.getTipoCuenta());
        cuentaDTO.setEstadoCuenta(cuenta.getEstadoCuenta());
        cuentaDTO.setCliente(cuenta.getCliente() == null ? null : cuenta.getCliente().getId());
        return cuentaDTO;
    }

    private Cuenta mapToEntity(final CuentaDTO cuentaDTO, final Cuenta cuenta) {
        cuenta.setNumeroCuenta(cuentaDTO.getNumeroCuenta());
        cuenta.setSaldo(cuentaDTO.getSaldo());
        cuenta.setTipoCuenta(cuentaDTO.getTipoCuenta());
        cuenta.setEstadoCuenta(cuentaDTO.getEstadoCuenta());
        final Cliente cliente = cuentaDTO.getCliente() == null ? null : clienteRepository.findById(cuentaDTO.getCliente())
                .orElseThrow(() -> new NotFoundException("cliente not found"));
        cuenta.setCliente(cliente);
        return cuenta;
    }

    public boolean numeroCuentaExists(final String numeroCuenta) {
        return cuentaRepository.existsByNumeroCuentaIgnoreCase(numeroCuenta);
    }

}
