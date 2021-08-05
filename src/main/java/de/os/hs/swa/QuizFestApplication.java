package de.os.hs.swa;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.eclipse.microprofile.openapi.annotations.Components;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;


@ApplicationPath("/quiz-fest/api")
@OpenAPIDefinition(
    info = @Info(
        title = "Quiz-Fest Api",
        version = "1.0"
    ),
    security = @SecurityRequirement(
        name = "Keycloak Accesstoken"
    ),
    components = @Components(
        securitySchemes = {
            @SecurityScheme(
                securitySchemeName = "Keycloak Accesstoken",
                type = SecuritySchemeType.HTTP,
                scheme = "bearer"
            )
        }
    )
)
public class QuizFestApplication extends Application {
    //empty class that extendes Application for OpenApi Documentation
}
