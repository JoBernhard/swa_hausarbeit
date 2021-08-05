package de.os.hs.swa;

import org.junit.jupiter.api.Test;

import io.quarkus.test.TestTransaction;
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
    @TestSecurity(user = "laupeter")
    @TestTransaction
    public void playQuizCorrectAnswerOk(){
        Long quizId = 200L;
        int questionNr = 1;
        int answerIndex = 1;

        given().contentType(ContentType.JSON)
        .post("/quiz-fest/api/quizzes/"+quizId+"/play/"+questionNr+"/"+answerIndex)
        .then()
        .statusCode(200);
    }

    @Test
    public void playQuizWrongAnswerOk(){
        Long quizId = 200L;
        int questionNr = 1;
        int answerIndex = 2;
        
        given().contentType(ContentType.JSON)
        .post("/quiz-fest/api/quizzes/"+quizId+"/play/"+questionNr+"/"+answerIndex)
        .then()
        .statusCode(200);
    }

    @Test
    public void playQuizQuestionNumberNotFound(){
        Long quizId = 200L;
        int questionNr = 0;
        int answerIndex = 1;
        
        given().contentType(ContentType.JSON)
        .post("/quiz-fest/api/quizzes/"+quizId+"/play/"+questionNr+"/"+answerIndex)
        .then()
        .statusCode(404);
    }

    @Test
    public void playQuizAnswerIndexNotFound(){
        Long quizId = 200L;
        int questionNr = 1;
        int answerIndex = 5;
        
        given().contentType(ContentType.JSON)
        .post("/quiz-fest/api/quizzes/"+quizId+"/play/"+questionNr+"/"+answerIndex)
        .then()
        .statusCode(404);
    }

    @Test
    public void playQuizIDNotFound(){
        Long quizId = 0L;
        int questionNr = 1;
        int answerIndex = 1;
        
        given().contentType(ContentType.JSON)
        .post("/quiz-fest/api/quizzes/"+quizId+"/play/"+questionNr+"/"+answerIndex)
        .then()
        .statusCode(404);
    }


    //Abfagen einer Frage Tests
    @Test
    public void getQuizToPlayOk(){
        Long quizId = 200L;
        
        given().when()
        .get("/quiz-fest/api/quizzes/"+quizId+"/play")
        .then()
        .statusCode(200);
    }

    @Test
    public void getQuizIDNotFound() {
        Long quizId = 0L;
        int questionNr = 1;
        
        given().when()
        .get("/quiz-fest/api/quizzes/"+quizId+"/play/"+questionNr)
        .then()
        .statusCode(404);
    }

    @Test
    public void getQuestionToPlayNumberNotFound() {
        Long quizId = 200L;
        int questionNr = 5;
        
        given().when()
        .get("/quiz-fest/api/quizzes/"+quizId+"/play/"+questionNr)
        .then()
        .statusCode(404);
    }
}
