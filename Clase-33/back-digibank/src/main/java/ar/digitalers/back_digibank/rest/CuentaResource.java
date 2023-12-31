package ar.digitalers.back_digibank.rest;

import ar.digitalers.back_digibank.model.CuentaDTO;
import ar.digitalers.back_digibank.service.CuentaService;
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
@RequestMapping(value = "/api/cuentas", produces = MediaType.APPLICATION_JSON_VALUE)
public class CuentaResource {

    private final CuentaService cuentaService;

    public CuentaResource(final CuentaService cuentaService) {
        this.cuentaService = cuentaService;
    }

    @GetMapping
    public ResponseEntity<List<CuentaDTO>> getAllCuentas() {
        return ResponseEntity.ok(cuentaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CuentaDTO> getCuenta(@PathVariable final Long id) {
        return ResponseEntity.ok(cuentaService.get(id));
    }

    @PostMapping
    public ResponseEntity<Long> createCuenta(@RequestBody @Valid final CuentaDTO cuentaDTO) {
        final Long createdId = cuentaService.create(cuentaDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateCuenta(@PathVariable final Long id,
            @RequestBody @Valid final CuentaDTO cuentaDTO) {
        cuentaService.update(id, cuentaDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCuenta(@PathVariable final Long id) {
        cuentaService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
