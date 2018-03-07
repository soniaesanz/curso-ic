package com.grupoesfera.demo.resource;

import com.grupoesfera.demo.domain.Client;
import com.grupoesfera.demo.exceptions.ResourceNotFoundException;
import com.grupoesfera.demo.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/clients")
public class ClientResource {

    @Autowired
    private ClientRepository clienteRepository;


    @PostMapping(consumes = "application/json")
    public ResponseEntity<Client> createClient(@RequestBody Client client) {

        return saveOrUpdateClientResponseEntity(client);
    }

    @PutMapping (consumes = "application/json")
    public ResponseEntity<Client> udateClient(@RequestBody Client client) {

        return saveOrUpdateClientResponseEntity(client);
    }

    private ResponseEntity<Client> saveOrUpdateClientResponseEntity(@RequestBody Client client) {
        return  ResponseEntity.ok(
                clienteRepository.save(client)
        );
    }


    @GetMapping("/{id}")
    public ResponseEntity<Client> find(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(
                clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("client", "id", id))
        );
    }

    @DeleteMapping (consumes = "application/json")
    public ResponseEntity<Client> delete(@RequestBody Client client) {

        clienteRepository.delete(client);
        return ResponseEntity.ok(client);
    }

}
