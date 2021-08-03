package de.os.hs.swa;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.postgresql.copy.CopyManager;
import org.postgresql.core.BaseConnection;

import de.os.hs.swa.category.entity.Category;
import de.os.hs.swa.quiz.control.DOTs.AnswerDTO;
import de.os.hs.swa.quiz.control.DOTs.QuestionDTO;
import de.os.hs.swa.quiz.control.DOTs.QuizEditDTO;
import de.os.hs.swa.quiz.entity.Answer;
import de.os.hs.swa.quiz.entity.Question;
import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import javax.ws.rs.BadRequestException;

// @author: Laura Peter
@QuarkusTest @TestSecurity(authorizationEnabled = false)
public class QuizRessourceTest {

    private static String categoryName="'Natur'";
    private static String title = "Naturquiz";
    private static String questionTitle = "Was ist keine Zimmerpflanze?";
    private static String firtstAnswerText = "Baum";
    private static String secondAnswerText = "Aloe Vera";

    

    @Test
    @TestTransaction
    public void createQuizOk(){
        ArrayList<AnswerDTO> answers = new ArrayList<>();
        answers.add(new AnswerDTO(firtstAnswerText, true));
        answers.add(new AnswerDTO(secondAnswerText, false));
        QuizEditDTO quiz = createQuiz(categoryName, title, createQuestion(answers));
        given().contentType(ContentType.JSON)
        .body(quiz)
        .post("/quizzes")
        .then()
        .statusCode(201);
    }

    @Test
    @TestTransaction
    public void createQuizNoTitle(){
        
        ArrayList<AnswerDTO> answers = new ArrayList<>();
        answers.add(new AnswerDTO(firtstAnswerText, true));
        answers.add(new AnswerDTO(secondAnswerText, false));
        QuizEditDTO quiz = createQuiz(categoryName, "", createQuestion(answers));

        try{
        given().contentType(ContentType.JSON)
        .body(quiz)
        .post("/quizzes")
        .then()
        .statusCode(400);
        }catch(BadRequestException e){

        }
    }
/*
    @Test
    @TestTransaction
    public void createQuizInvalidCategory(){
        ArrayList<AnswerDTO> answers = new ArrayList<>();
        answers.add(new AnswerDTO(firtstAnswerText, 1, true));
        answers.add(new AnswerDTO(secondAnswerText, 2, false));
        QuizEditDTO quiz = createQuiz("", title, createQuestion(answers) );
        given().contentType(ContentType.JSON)
        .body(quiz)
        .post("/quizzes")
        .then()
        .statusCode(400);
    }
    
    @Test
    @TestTransaction
    public void createQuizOneAnswer(){
        
        ArrayList<AnswerDTO> answers = new ArrayList<>();
        answers.add(new AnswerDTO(firtstAnswerText, 1, true));
        QuizEditDTO quiz = createQuiz(categoryName, title, createQuestion(answers));
        given().contentType(ContentType.JSON)
        .body(quiz)
        .post("/quizzes")
        .then()
        .statusCode(400);
    }

    @Test
    @TestTransaction
    public void createQuizNoQuestion(){
        QuizEditDTO quiz = createQuiz(categoryName, title, null);
        given().contentType(ContentType.JSON)
        .body(quiz)
        .post("/quizzes")
        .then()
        .statusCode(400);
    }

    @Test
    @TestTransaction
    public void createQuizInvalidAnswerText(){
        ArrayList<AnswerDTO> answers = new ArrayList<>();
        answers.add(new AnswerDTO(firtstAnswerText, 1, true));
        answers.add(new AnswerDTO("", 2, false));
        QuizEditDTO quiz = createQuiz(categoryName, title, createQuestion(answers));
        given().contentType(ContentType.JSON)
        .body(quiz)
        .post("/quizzes")
        .then()
        .statusCode(400);
    }

    @Test
    @TestTransaction
    public void createQuizNoCorrectAnswer(){
        ArrayList<AnswerDTO> answers = new ArrayList<>();
        answers.add(new AnswerDTO(firtstAnswerText, 1, true));
        answers.add(new AnswerDTO("", 2, false));
        QuizEditDTO quiz = createQuiz(categoryName, title, createQuestion(answers));
        given().contentType(ContentType.JSON)
        .body(quiz)
        .post("/quizzes")
        .then()
        .statusCode(400);
    }*/

    public QuestionDTO createQuestion(Collection<AnswerDTO> answers){
        return new QuestionDTO(questionTitle, answers);
    }

    public QuizEditDTO createQuiz(String categoryName, String title, QuestionDTO question){
        
        ArrayList<QuestionDTO> questions = new ArrayList<QuestionDTO>();
        questions.add(question);
        Category c = new Category();
        c.setName(categoryName);
        return new QuizEditDTO(c, title, questions);
    }


    @Test
    @TestSecurity(user = "theErstellerIn")
    public void getOwnQuizzesOk(){
        given().contentType(ContentType.JSON)
        .get("/quizzes")
        .then()
        .statusCode(200);
    }

    @Test
    @TestSecurity(user = "theSpielerIn")
    public void getOwnQuizzesNoContent(){
        given().contentType(ContentType.JSON)
        .get("/quizzes")
        .then()
        .statusCode(204);
    }

    @Test
    @TestSecurity(user = "")
    public void getOwnQuizzesNotLoggedIn(){
        given().contentType(ContentType.JSON)
        .get("/quizzes")
        .then()
        .statusCode(403);
    }


}
