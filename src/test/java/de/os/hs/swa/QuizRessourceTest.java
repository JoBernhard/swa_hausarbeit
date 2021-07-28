package de.os.hs.swa;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import static io.restassured.RestAssured.given;

@QuarkusTest
public class QuizRessourceTest {
    
    @Test
    public void getQuizOk(){
        Long quizId = 1L;
        given()
          .when().get("/quizzes/"+quizId)
          .then().statusCode(200);
    }

    @Test
    public void getQuizNotFound(){
        Long quizId = 0L;
        given()
          .when().get("/quizzes/"+quizId)
          .then().statusCode(404);
    }
}
