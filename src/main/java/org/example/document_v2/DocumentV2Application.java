package org.example.document_v2;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(title = "Starbuck API", version = "1.0.0"),
        servers = {@Server(url = "http://localhost:8080")},
        tags = {@Tag(name = "Coffee", description = "Operation about Coffee")})

public class DocumentV2Application {

    public static void main(String[] args) {
        SpringApplication.run(DocumentV2Application.class, args);
    }

}
