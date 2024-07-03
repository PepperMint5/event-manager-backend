package com.dauphine.event_manager_backend;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Event manager backend",
                description = "Even manager endpoints and apis",
                contact = @Contact(name = "Alice Boursin", email = "alice.boursin@dauphine.eu"),
                version = "1.0.0"
        )
)
public class EventManagerBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(EventManagerBackendApplication.class, args);
    }

}
