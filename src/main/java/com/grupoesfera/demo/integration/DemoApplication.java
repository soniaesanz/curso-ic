package com.grupoesfera.demo.integration;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@EnableCaching
@ComponentScan( "com.grupoesfera.demo")
public class DemoApplication {


	public static void main(String[] args) { SpringApplication.run(DemoApplication.class, args); }

}
