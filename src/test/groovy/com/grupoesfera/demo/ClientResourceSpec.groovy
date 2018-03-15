import com.grupoesfera.demo.integration.exceptions.ResourceNotFoundException
import com.grupoesfera.demo.integration.repository.ClientRepository
import com.grupoesfera.demo.integration.resource.ClientResource
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.http.HttpStatus.*

class ClientResourceSpec extends Specification{


    def clientResource = new ClientResource()
    def clientRepository = Mock(ClientRepository)

    //The magic happens here
    MockMvc mockMvc = standaloneSetup(clientResource).build()


    def setup() {
        clientResource.clienteRepository = clientRepository
    }

    def "el get sobre un recurso no encontrado debe responder un status 404"() {

        when:
        def response = mockMvc.perform(get('/clients/v1/1')).andReturn().response

        then:
        1 * clientRepository.findById(1) >> Optional.empty()
        response.status == NOT_FOUND.value()
    }


    def 'El metodo find de un objeto no encotrado debe arrojar una exception'(){

        when: 'se busca un objeto que no existe'
        def response = clientResource.find(1)

        then:
        1 * clientRepository.findById(1) >> Optional.empty()
        ResourceNotFoundException ex = thrown()
        ex.resourceName == 'client'
        ex.fieldName == 'id'
        ex.fieldValue == 1
}


}
