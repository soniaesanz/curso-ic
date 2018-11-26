package ar.com.grupoesfera.demo.aceptacion;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty"}, features="src/acceptance-test/resources")
public class EjecutorDeCucumberTest {

}
