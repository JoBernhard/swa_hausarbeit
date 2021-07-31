package de.os.hs.swa;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.postgresql.copy.CopyManager;
import org.postgresql.core.BaseConnection;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.transaction.Transactional;


//author: Johanna Bernhard
@QuarkusTest
@TestSecurity(authorizationEnabled = false)
public class PlayRessourceTest{
/*
    @BeforeAll
    public static void init(){
        try {
            copyIntoTestDB("Category", "./testcategories.csv");
            copyIntoTestDB("Quiz", "./testquiz.csv");
            copyIntoTestDB("Question", "./testquestion.csv");
            copyIntoTestDB("Answer", "./testanswer.csv");
        } catch (Exception e) {
            e.printStackTrace();
        } 
       
    }

    @TestTransaction
    private static void copyIntoTestDB(String tablename, String file){
        try {
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/quizfestdb","postgres","annie_box");
            Statement statement = con.createStatement(); 
                statement.executeUpdate("TRUNCATE " + tablename+" cascade");          
            long rowsInserted = new CopyManager((BaseConnection) con)
            .copyIn("COPY "+tablename+" FROM STDIN (FORMAT csv, HEADER)", new FileReader(file));
            System.out.printf("%d row(s) inserted%n", rowsInserted);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }*/

    // Frage beantworten Tests
    @Test @Transactional
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
