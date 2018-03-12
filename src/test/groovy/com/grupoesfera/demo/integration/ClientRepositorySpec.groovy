package com.grupoesfera.demo.integration

import com.grupoesfera.demo.integration.domain.Client
import com.grupoesfera.demo.integration.repository.ClientRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import spock.lang.Specification

@DataJpaTest
class ClientRepositorySpec extends Specification{


    @Autowired
    private ClientRepository clientRepository

   Client createClient(){

       def client = new Client();
       client.name = 'juan'
       client.lastName = 'grillo'

       client
    }

    
    def "find client by name"() {

        def savedClient = clientRepository.save(createClient())

        when: "find by name juan"
        Optional<Client> searchedClient = clientRepository.findClientByName("juan")

        then: "saved and retrieved entity by name must be equal"
        searchedClient.present
        searchedClient.get() == savedClient
    }

}
