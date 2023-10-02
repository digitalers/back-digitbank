package ar.digitalers.back_digibank.service;

import ar.digitalers.back_digibank.domain.Cuenta;
import ar.digitalers.back_digibank.domain.Transaccion;
import ar.digitalers.back_digibank.model.TransaccionDTO;
import ar.digitalers.back_digibank.repos.CuentaRepository;
import ar.digitalers.back_digibank.repos.TransaccionRepository;
import ar.digitalers.back_digibank.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class TransaccionService {

    private final TransaccionRepository transaccionRepository;
    private final CuentaRepository cuentaRepository;

    public TransaccionService(final TransaccionRepository transaccionRepository,
            final CuentaRepository cuentaRepository) {
        this.transaccionRepository = transaccionRepository;
        this.cuentaRepository = cuentaRepository;
    }

    public List<TransaccionDTO> findAll() {
        final List<Transaccion> transaccions = transaccionRepository.findAll(Sort.by("id"));
        return transaccions.stream()
                .map(transaccion -> mapToDTO(transaccion, new TransaccionDTO()))
                .toList();
    }

    public TransaccionDTO get(final Long id) {
        return transaccionRepository.findById(id)
                .map(transaccion -> mapToDTO(transaccion, new TransaccionDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final TransaccionDTO transaccionDTO) {
        final Transaccion transaccion = new Transaccion();
        mapToEntity(transaccionDTO, transaccion);
        return transaccionRepository.save(transaccion).getId();
    }

    public void update(final Long id, final TransaccionDTO transaccionDTO) {
        final Transaccion transaccion = transaccionRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(transaccionDTO, transaccion);
        transaccionRepository.save(transaccion);
    }

    public void delete(final Long id) {
        transaccionRepository.deleteById(id);
    }

    private TransaccionDTO mapToDTO(final Transaccion transaccion,
            final TransaccionDTO transaccionDTO) {
        transaccionDTO.setId(transaccion.getId());
        transaccionDTO.setFechaTransaccion(transaccion.getFechaTransaccion());
        transaccionDTO.setTipoTransaccion(transaccion.getTipoTransaccion());
        transaccionDTO.setMonto(transaccion.getMonto());
        transaccionDTO.setCuentaOrigen(transaccion.getCuentaOrigen());
        transaccionDTO.setCuentaDestino(transaccion.getCuentaDestino());
        transaccionDTO.setCuentaOrigenId(transaccion.getCuentaOrigenId() == null ? null : transaccion.getCuentaOrigenId().getId());
        transaccionDTO.setCuentaDestinoId(transaccion.getCuentaDestinoId() == null ? null : transaccion.getCuentaDestinoId().getId());
        return transaccionDTO;
    }

    private Transaccion mapToEntity(final TransaccionDTO transaccionDTO,
            final Transaccion transaccion) {
        transaccion.setFechaTransaccion(transaccionDTO.getFechaTransaccion());
        transaccion.setTipoTransaccion(transaccionDTO.getTipoTransaccion());
        transaccion.setMonto(transaccionDTO.getMonto());
        transaccion.setCuentaOrigen(transaccionDTO.getCuentaOrigen());
        transaccion.setCuentaDestino(transaccionDTO.getCuentaDestino());
        final Cuenta cuentaOrigenId = transaccionDTO.getCuentaOrigenId() == null ? null : cuentaRepository.findById(transaccionDTO.getCuentaOrigenId())
                .orElseThrow(() -> new NotFoundException("cuentaOrigenId not found"));
        transaccion.setCuentaOrigenId(cuentaOrigenId);
        final Cuenta cuentaDestinoId = transaccionDTO.getCuentaDestinoId() == null ? null : cuentaRepository.findById(transaccionDTO.getCuentaDestinoId())
                .orElseThrow(() -> new NotFoundException("cuentaDestinoId not found"));
        transaccion.setCuentaDestinoId(cuentaDestinoId);
        return transaccion;
    }

}
