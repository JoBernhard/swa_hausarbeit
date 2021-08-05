package de.os.hs.swa;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;

import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;

@QuarkusTest
public class CategoryRessourceTest {
    
    @Test
    @TestSecurity(user = "laupeter")
    public void getCategoryNameExists(){
        String categoryName = "Natur";
        given().when().get("quiz-fest/api/category/"+categoryName)
        .then().statusCode(200);
    }

    @Test
    @TestSecurity(user = "laupeter")
    public void getCategoryNameDoesntExist(){
        String categoryName = "Unbekannt";
        given().when().get("quiz-fest/api/category/"+categoryName)
        .then().statusCode(404);
    }

}
