package com.grupoesfera.demo

import com.grupoesfera.demo.integration.domain.Client
import spock.lang.Specification

import static org.springframework.http.HttpStatus.NOT_FOUND
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get

class ClientSpec extends Specification{

    def "para probar integracion sonar cono groovy"() {

        def client = new Client()
        client.name = "juan"

        expect:
        client.name == "juan"
    }
}
