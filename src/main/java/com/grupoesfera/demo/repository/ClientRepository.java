package com.grupoesfera.demo.repository;

import com.grupoesfera.demo.domain.Client;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {



    @Cacheable(value = "client-get")
    Optional<Client> findById(Long id);


    @CacheEvict(value = "client-get", key = "#p0.id")
    void delete(Client id);


    @CacheEvict(value = "client-get",  key = "#p0.id")
    Client save(Client client);



    Page findAll(Pageable p);
}


