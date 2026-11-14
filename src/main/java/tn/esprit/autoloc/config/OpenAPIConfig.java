package tn.esprit.autoloc.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI autoLocOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("AutoLoc API - Gestion de Location de Véhicules")
                        .description("Documentation interactive des Web Services REST de l'application AutoLoc (Module Architecture des SI - ESPRIT)")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Équipe AutoLoc / UP ASI")
                                .email("support@autoloc.esprit.tn")));
    }
}
