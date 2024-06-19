package com.neo.neoTour.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Nargiza",
                        email = "nargizh03@gmail.com"
                ),
                title = "NeoTour",
                description = "API documentation for Tour Booking Application",
                version = "0.0.1"
        ),
        servers = {
                @Server(
                        description = "Railway Server",
                        url = "https://neotour-production-0f7a.up.railway.app"
                )
        }
)
public class OpenApiConfig {
}
