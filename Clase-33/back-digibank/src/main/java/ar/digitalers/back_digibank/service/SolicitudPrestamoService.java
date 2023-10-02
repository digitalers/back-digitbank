package ar.digitalers.back_digibank.service;

import ar.digitalers.back_digibank.domain.Cliente;
import ar.digitalers.back_digibank.domain.SolicitudPrestamo;
import ar.digitalers.back_digibank.model.SolicitudPrestamoDTO;
import ar.digitalers.back_digibank.repos.ClienteRepository;
import ar.digitalers.back_digibank.repos.SolicitudPrestamoRepository;
import ar.digitalers.back_digibank.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class SolicitudPrestamoService {

    private final SolicitudPrestamoRepository solicitudPrestamoRepository;
    private final ClienteRepository clienteRepository;

    public SolicitudPrestamoService(final SolicitudPrestamoRepository solicitudPrestamoRepository,
            final ClienteRepository clienteRepository) {
        this.solicitudPrestamoRepository = solicitudPrestamoRepository;
        this.clienteRepository = clienteRepository;
    }

    public List<SolicitudPrestamoDTO> findAll() {
        final List<SolicitudPrestamo> solicitudPrestamoes = solicitudPrestamoRepository.findAll(Sort.by("id"));
        return solicitudPrestamoes.stream()
                .map(solicitudPrestamo -> mapToDTO(solicitudPrestamo, new SolicitudPrestamoDTO()))
                .toList();
    }

    public SolicitudPrestamoDTO get(final Long id) {
        return solicitudPrestamoRepository.findById(id)
                .map(solicitudPrestamo -> mapToDTO(solicitudPrestamo, new SolicitudPrestamoDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final SolicitudPrestamoDTO solicitudPrestamoDTO) {
        final SolicitudPrestamo solicitudPrestamo = new SolicitudPrestamo();
        mapToEntity(solicitudPrestamoDTO, solicitudPrestamo);
        return solicitudPrestamoRepository.save(solicitudPrestamo).getId();
    }

    public void update(final Long id, final SolicitudPrestamoDTO solicitudPrestamoDTO) {
        final SolicitudPrestamo solicitudPrestamo = solicitudPrestamoRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(solicitudPrestamoDTO, solicitudPrestamo);
        solicitudPrestamoRepository.save(solicitudPrestamo);
    }

    public void delete(final Long id) {
        solicitudPrestamoRepository.deleteById(id);
    }

    private SolicitudPrestamoDTO mapToDTO(final SolicitudPrestamo solicitudPrestamo,
            final SolicitudPrestamoDTO solicitudPrestamoDTO) {
        solicitudPrestamoDTO.setId(solicitudPrestamo.getId());
        solicitudPrestamoDTO.setFechaSolicitud(solicitudPrestamo.getFechaSolicitud());
        solicitudPrestamoDTO.setMontoSolicitado(solicitudPrestamo.getMontoSolicitado());
        solicitudPrestamoDTO.setEstadoSolicitudPrestamo(solicitudPrestamo.getEstadoSolicitudPrestamo());
        solicitudPrestamoDTO.setCliente(solicitudPrestamo.getCliente() == null ? null : solicitudPrestamo.getCliente().getId());
        return solicitudPrestamoDTO;
    }

    private SolicitudPrestamo mapToEntity(final SolicitudPrestamoDTO solicitudPrestamoDTO,
            final SolicitudPrestamo solicitudPrestamo) {
        solicitudPrestamo.setFechaSolicitud(solicitudPrestamoDTO.getFechaSolicitud());
        solicitudPrestamo.setMontoSolicitado(solicitudPrestamoDTO.getMontoSolicitado());
        solicitudPrestamo.setEstadoSolicitudPrestamo(solicitudPrestamoDTO.getEstadoSolicitudPrestamo());
        final Cliente cliente = solicitudPrestamoDTO.getCliente() == null ? null : clienteRepository.findById(solicitudPrestamoDTO.getCliente())
                .orElseThrow(() -> new NotFoundException("cliente not found"));
        solicitudPrestamo.setCliente(cliente);
        return solicitudPrestamo;
    }

}
