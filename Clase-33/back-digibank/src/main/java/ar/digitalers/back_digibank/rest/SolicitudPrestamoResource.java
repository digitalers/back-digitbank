package ar.digitalers.back_digibank.rest;

import ar.digitalers.back_digibank.model.SolicitudPrestamoDTO;
import ar.digitalers.back_digibank.service.SolicitudPrestamoService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/solicitudPrestamos", produces = MediaType.APPLICATION_JSON_VALUE)
public class SolicitudPrestamoResource {

    private final SolicitudPrestamoService solicitudPrestamoService;

    public SolicitudPrestamoResource(final SolicitudPrestamoService solicitudPrestamoService) {
        this.solicitudPrestamoService = solicitudPrestamoService;
    }

    @GetMapping
    public ResponseEntity<List<SolicitudPrestamoDTO>> getAllSolicitudPrestamos() {
        return ResponseEntity.ok(solicitudPrestamoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SolicitudPrestamoDTO> getSolicitudPrestamo(@PathVariable final Long id) {
        return ResponseEntity.ok(solicitudPrestamoService.get(id));
    }

    @PostMapping
    public ResponseEntity<Long> createSolicitudPrestamo(
            @RequestBody @Valid final SolicitudPrestamoDTO solicitudPrestamoDTO) {
        final Long createdId = solicitudPrestamoService.create(solicitudPrestamoDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateSolicitudPrestamo(@PathVariable final Long id,
            @RequestBody @Valid final SolicitudPrestamoDTO solicitudPrestamoDTO) {
        solicitudPrestamoService.update(id, solicitudPrestamoDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSolicitudPrestamo(@PathVariable final Long id) {
        solicitudPrestamoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
