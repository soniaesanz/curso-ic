package com.grupoesfera.demo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@EnableCaching
@ComponentScan("com.grupoesfera.demo")
public class DemoApplication {


	public static void main(String[] args) { SpringApplication.run(DemoApplication.class, args); }

}
