package com.grupoesfera.demo.resource;

import com.grupoesfera.demo.domain.Client;
import com.grupoesfera.demo.exceptions.ResourceNotFoundException;
import com.grupoesfera.demo.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientRepository clienteRepository;


    @PostMapping(consumes = "application/json")
    public ResponseEntity<Client> createClient(@RequestBody Client client) {

        return  ResponseEntity.ok(
                    clienteRepository.save(client)
        );
    }


    @GetMapping("/{id}")
    public ResponseEntity<Client> find(@PathVariable(value = "id") Long clientId) {
        return ResponseEntity.ok(
                clienteRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", clientId))
        );
    }

}
