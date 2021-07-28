package de.os.hs.swa;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;

@QuarkusTest
public class CategoryRessourceTest {
    
    @Test
    public void getCategoryNameExists(){
        String categoryName = "Natur";
        given().when().get("/category/"+categoryName)
        .then().statusCode(200);
    }

    @Test
    public void getCategoryNameDoesntExist(){
        String categoryName = "Unbekannt";
        given().when().get("/category/"+categoryName)
        .then().statusCode(404);
    }

}
