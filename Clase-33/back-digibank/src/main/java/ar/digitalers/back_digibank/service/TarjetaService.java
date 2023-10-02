package ar.digitalers.back_digibank.service;

import ar.digitalers.back_digibank.domain.Cliente;
import ar.digitalers.back_digibank.domain.Tarjeta;
import ar.digitalers.back_digibank.model.TarjetaDTO;
import ar.digitalers.back_digibank.repos.ClienteRepository;
import ar.digitalers.back_digibank.repos.TarjetaRepository;
import ar.digitalers.back_digibank.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class TarjetaService {

    private final TarjetaRepository tarjetaRepository;
    private final ClienteRepository clienteRepository;

    public TarjetaService(final TarjetaRepository tarjetaRepository,
            final ClienteRepository clienteRepository) {
        this.tarjetaRepository = tarjetaRepository;
        this.clienteRepository = clienteRepository;
    }

    public List<TarjetaDTO> findAll() {
        final List<Tarjeta> tarjetas = tarjetaRepository.findAll(Sort.by("id"));
        return tarjetas.stream()
                .map(tarjeta -> mapToDTO(tarjeta, new TarjetaDTO()))
                .toList();
    }

    public TarjetaDTO get(final Long id) {
        return tarjetaRepository.findById(id)
                .map(tarjeta -> mapToDTO(tarjeta, new TarjetaDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final TarjetaDTO tarjetaDTO) {
        final Tarjeta tarjeta = new Tarjeta();
        mapToEntity(tarjetaDTO, tarjeta);
        return tarjetaRepository.save(tarjeta).getId();
    }

    public void update(final Long id, final TarjetaDTO tarjetaDTO) {
        final Tarjeta tarjeta = tarjetaRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(tarjetaDTO, tarjeta);
        tarjetaRepository.save(tarjeta);
    }

    public void delete(final Long id) {
        tarjetaRepository.deleteById(id);
    }

    private TarjetaDTO mapToDTO(final Tarjeta tarjeta, final TarjetaDTO tarjetaDTO) {
        tarjetaDTO.setId(tarjeta.getId());
        tarjetaDTO.setNumeroTarjeta(tarjeta.getNumeroTarjeta());
        tarjetaDTO.setTipoTarjeta(tarjeta.getTipoTarjeta());
        tarjetaDTO.setFechaVencimiento(tarjeta.getFechaVencimiento());
        tarjetaDTO.setSaldoDisponible(tarjeta.getSaldoDisponible());
        tarjetaDTO.setCliente(tarjeta.getCliente() == null ? null : tarjeta.getCliente().getId());
        return tarjetaDTO;
    }

    private Tarjeta mapToEntity(final TarjetaDTO tarjetaDTO, final Tarjeta tarjeta) {
        tarjeta.setNumeroTarjeta(tarjetaDTO.getNumeroTarjeta());
        tarjeta.setTipoTarjeta(tarjetaDTO.getTipoTarjeta());
        tarjeta.setFechaVencimiento(tarjetaDTO.getFechaVencimiento());
        tarjeta.setSaldoDisponible(tarjetaDTO.getSaldoDisponible());
        final Cliente cliente = tarjetaDTO.getCliente() == null ? null : clienteRepository.findById(tarjetaDTO.getCliente())
                .orElseThrow(() -> new NotFoundException("cliente not found"));
        tarjeta.setCliente(cliente);
        return tarjeta;
    }

}
