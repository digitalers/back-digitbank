package ar.digitalers.back_digibank.rest;

import ar.digitalers.back_digibank.model.ClienteDTO;
import ar.digitalers.back_digibank.service.ClienteService;
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
@RequestMapping(value = "/api/clientes", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClienteResource {

    private final ClienteService clienteService;

    public ClienteResource(final ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> getAllClientes() {
        return ResponseEntity.ok(clienteService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> getCliente(@PathVariable final Long id) {
        return ResponseEntity.ok(clienteService.get(id));
    }

    @PostMapping
    public ResponseEntity<Long> createCliente(@RequestBody @Valid final ClienteDTO clienteDTO) {
        final Long createdId = clienteService.create(clienteDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateCliente(@PathVariable final Long id,
            @RequestBody @Valid final ClienteDTO clienteDTO) {
        clienteService.update(id, clienteDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable final Long id) {
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
