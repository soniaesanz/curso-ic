package com.grupoesfera.demo.integration.resource;

import com.grupoesfera.demo.integration.domain.Client;
import com.grupoesfera.demo.integration.exceptions.ResourceNotFoundException;
import com.grupoesfera.demo.integration.repository.ClientRepository;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/clients")
public class ClientResource {

    private static final Logger log = LoggerFactory.getLogger(ClientResource.class);

    @Autowired
    private ClientRepository clienteRepository;

    @PostMapping(value = "/v1.0")
    public ResponseEntity<Client> createClient(@RequestBody Client client) {

        log.info("create client with name [{}] and lastname [{}]",client.getName(),client.getLastName());
        return saveOrUpdateClientResponseEntity(client);
    }

    @PutMapping (value = "/v1.0")
    public ResponseEntity<Client> udateClient(@RequestBody Client client) {

        log.info("updating client, {}", client);
        return saveOrUpdateClientResponseEntity(client);
    }

    private ResponseEntity<Client> saveOrUpdateClientResponseEntity(@RequestBody Client client) {
        return  ResponseEntity.ok(
                clienteRepository.save(client)
        );
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
                    value = "Results page you want to retrieve (0..N)"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                    value = "Number of records per page."),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "Sorting criteria in the format: property(,asc|desc). " +
                            "Default sort order is ascending. " +
                            "Multiple sort criteria are supported.")
    })
    @GetMapping("/v1.0")
    public ResponseEntity<Page> findAll(Pageable p){

        return ResponseEntity.ok(
          clienteRepository.findAll(p)
        );
    }

    @GetMapping("/v1.0/{id}")
    public ResponseEntity<Client> find(@PathVariable(value = "id") Long id) {
        log.info("searchig for client with, {}", id);
        return ResponseEntity.ok(
                clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("client", "id", id))
        );
    }

    @GetMapping("/v1.0/")
    public ResponseEntity<Client> find(@RequestParam(value = "name") String name) {
        log.info("searchig for client with, {}", name);
        return ResponseEntity.ok(
                clienteRepository.findClientByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("client", "name", name))
        );
    }

    @DeleteMapping (value = "/v1.0")
    public ResponseEntity<Client> delete(@RequestBody Client client) {

        log.info("deleting client, {}", client);
        clienteRepository.delete(client);
        return ResponseEntity.ok(client);
    }

}
