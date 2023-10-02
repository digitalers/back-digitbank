package ar.digitalers.back_digibank.rest;

import ar.digitalers.back_digibank.model.TarjetaDTO;
import ar.digitalers.back_digibank.service.TarjetaService;
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
@RequestMapping(value = "/api/tarjetas", produces = MediaType.APPLICATION_JSON_VALUE)
public class TarjetaResource {

    private final TarjetaService tarjetaService;

    public TarjetaResource(final TarjetaService tarjetaService) {
        this.tarjetaService = tarjetaService;
    }

    @GetMapping
    public ResponseEntity<List<TarjetaDTO>> getAllTarjetas() {
        return ResponseEntity.ok(tarjetaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TarjetaDTO> getTarjeta(@PathVariable final Long id) {
        return ResponseEntity.ok(tarjetaService.get(id));
    }

    @PostMapping
    public ResponseEntity<Long> createTarjeta(@RequestBody @Valid final TarjetaDTO tarjetaDTO) {
        final Long createdId = tarjetaService.create(tarjetaDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateTarjeta(@PathVariable final Long id,
            @RequestBody @Valid final TarjetaDTO tarjetaDTO) {
        tarjetaService.update(id, tarjetaDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTarjeta(@PathVariable final Long id) {
        tarjetaService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
