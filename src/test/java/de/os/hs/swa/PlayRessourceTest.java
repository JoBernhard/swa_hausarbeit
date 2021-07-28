package de.os.hs.swa;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;


//author: Johanna Bernhard
@QuarkusTest
@TestSecurity(authorizationEnabled = false)
public class PlayRessourceTest{

    // Frage beantworten Tests
    @Test
    public void playQuizCorrectAnswerOk(){
        Long quizId = 1L;
        int questionNr = 1;
        int answerIndex = 1;

        given().contentType(ContentType.JSON)
        .post("/quizzes/"+quizId+"/play/"+questionNr+"/"+answerIndex)
        .then()
        .statusCode(200);
    }

    @Test
    public void playQuizWrongAnswerOk(){
        Long quizId = 1L;
        int questionNr = 1;
        int answerIndex = 2;
        
        given().contentType(ContentType.JSON)
        .post("/quizzes/"+quizId+"/play/"+questionNr+"/"+answerIndex)
        .then()
        .statusCode(200);
    }

    @Test
    public void playQuizQuestionNumberNotFound(){
        Long quizId = 1L;
        int questionNr = 0;
        int answerIndex = 1;
        
        given().contentType(ContentType.JSON)
        .post("/quizzes/"+quizId+"/play/"+questionNr+"/"+answerIndex)
        .then()
        .statusCode(404);
    }

    @Test
    public void playQuizAnswerIndexNotFound(){
        Long quizId = 1L;
        int questionNr = 1;
        int answerIndex = 5;
        
        given().contentType(ContentType.JSON)
        .post("/quizzes/"+quizId+"/play/"+questionNr+"/"+answerIndex)
        .then()
        .statusCode(404);
    }

    @Test
    public void playQuizIDNotFound(){
        Long quizId = 2L;
        int questionNr = 1;
        int answerIndex = 1;
        
        given().contentType(ContentType.JSON)
        .post("/quizzes/"+quizId+"/play/"+questionNr+"/"+answerIndex)
        .then()
        .statusCode(404);
    }


    //Abfagen einer Frage Tests
    @Test
    public void getQuizToPlayOk(){
        Long quizId = 1L;
        int questionNr = 1;
        
        given().when()
        .get("/quizzes/"+quizId+"/play/"+questionNr)
        .then()
        .statusCode(200);
    }

    @Test
    public void getQuizIDNotFound() {
        Long quizId = 0L;
        int questionNr = 1;
        
        given().when()
        .get("/quizzes/"+quizId+"/play/"+questionNr)
        .then()
        .statusCode(404);
    }

    @Test
    public void getQuestionToPlayNumberNotFound() {
        Long quizId = 1L;
        int questionNr = 5;
        
        given().when()
        .get("/quizzes/"+quizId+"/play/"+questionNr)
        .then()
        .statusCode(404);
    }
}