package de.os.hs.swa;


import org.hibernate.AssertionFailure;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.postgresql.copy.CopyManager;
import org.postgresql.core.BaseConnection;

import de.os.hs.swa.category.entity.Category;
import de.os.hs.swa.quiz.boundary.QuizzesRessource;
import de.os.hs.swa.quiz.control.DOTs.AnswerDTO;
import de.os.hs.swa.quiz.control.DOTs.QuestionDTO;
import de.os.hs.swa.quiz.control.DOTs.QuizEditDTO;
import de.os.hs.swa.quiz.entity.Answer;
import de.os.hs.swa.quiz.entity.Question;
import io.quarkus.test.TestTransaction;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.smallrye.common.constraint.Assert;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

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

    private static String categoryName="Natur";
    private static String title = "Naturquiz";
    private static String questionTitle = "Was ist keine Zimmerpflanze?";
    private static String firtstAnswerText = "Baum";
    private static String secondAnswerText = "Aloe Vera";

    @BeforeAll
	public static void setup() {
	}

    @Test
    @TestTransaction
    @TestSecurity(user = "laupeter")
    public void createQuizOk(){
        ArrayList<AnswerDTO> answers = new ArrayList<>();
        answers.add(new AnswerDTO(firtstAnswerText, true));
        answers.add(new AnswerDTO(secondAnswerText, false));
        QuizEditDTO quiz = createQuiz(categoryName, title, createQuestion(answers));
        given().contentType(ContentType.JSON)
        .body(quiz)
        .post("/quiz-fest/api/quizzes")
        .then()
        .statusCode(201);
    }

    @Test
    @TestTransaction @TestSecurity(user = "laupeter")
    public void createQuizNoTitle(){
        
        ArrayList<AnswerDTO> answers = new ArrayList<>();
        answers.add(new AnswerDTO(firtstAnswerText, true));
        answers.add(new AnswerDTO(secondAnswerText, false));
        QuizEditDTO quiz = createQuiz(categoryName, "", createQuestion(answers));
        given().contentType(ContentType.JSON)
            .body(quiz)
            .post("/quiz-fest/api/quizzes").then().statusCode(400);
    }

    @Test
    @TestTransaction
    @TestSecurity(user = "laupeter")
    public void createQuizInvalidCategory(){
        ArrayList<AnswerDTO> answers = new ArrayList<>();
        answers.add(new AnswerDTO(firtstAnswerText, true));
        answers.add(new AnswerDTO(secondAnswerText, false));
        QuizEditDTO quiz = createQuiz("", title, createQuestion(answers) );
        given().contentType(ContentType.JSON)
        .body(quiz)
        .post("/quiz-fest/api/quizzes")
        .then()
        .statusCode(400);
    }
    
    
    @Test
    @TestTransaction
    @TestSecurity(user = "laupeter")
    public void createQuizOneAnswer(){
        ArrayList<AnswerDTO> answers = new ArrayList<>();
        answers.add(new AnswerDTO(firtstAnswerText, true));
        QuizEditDTO quiz = createQuiz(categoryName, title, createQuestion(answers));
        given().contentType(ContentType.JSON)
        .body(quiz)
        .post("/quiz-fest/api/quizzes")
        .then()
        .statusCode(400);
    }

    @Test
    @TestTransaction
    @TestSecurity(user = "laupeter")
    public void createQuizNoQuestion(){
        QuizEditDTO quiz = createQuiz(categoryName, title, null);
        given().contentType(ContentType.JSON)
        .body(quiz)
        .post("/quiz-fest/api/quizzes")
        .then()
        .statusCode(400);
    }

    @Test
    @TestTransaction
    @TestSecurity(user = "laupeter")
    public void createQuizInvalidAnswerText(){
        ArrayList<AnswerDTO> answers = new ArrayList<>();
        answers.add(new AnswerDTO(firtstAnswerText, true));
        answers.add(new AnswerDTO("", false));
        QuizEditDTO quiz = createQuiz(categoryName, title, createQuestion(answers));
        given().contentType(ContentType.JSON)
        .body(quiz)
        .post("/quiz-fest/api/quizzes")
        .then()
        .statusCode(400);
    }

    @Test
    @TestTransaction
    @TestSecurity(user = "laupeter")
    public void createQuizNoCorrectAnswer(){
        ArrayList<AnswerDTO> answers = new ArrayList<>();
        answers.add(new AnswerDTO(firtstAnswerText, true));
        answers.add(new AnswerDTO("", false));
        QuizEditDTO quiz = createQuiz(categoryName, title, createQuestion(answers));
        given().contentType(ContentType.JSON)
        .body(quiz)
        .post("/quiz-fest/api/quizzes")
        .then()
        .statusCode(400);
    }

    public Collection<QuestionDTO> createQuestion(Collection<AnswerDTO> answers){
        ArrayList<QuestionDTO> questions = new ArrayList<>();
        questions.add(new QuestionDTO(questionTitle, answers));
        return questions;
    }

    public QuizEditDTO createQuiz(String categoryName, String title, Collection<QuestionDTO> questions){
               
        Category c = new Category();
        c.setName(categoryName);
        return new QuizEditDTO(c, title, questions);
    }


    @Test
    @TestSecurity(user = "laupeter")
    public void getOwnQuizzesOk(){
        given().contentType(ContentType.JSON)
        .get("/quiz-fest/api/quizzes")
        .then()
        .statusCode(200);
    }

    @Test
    @TestSecurity(user = "admin")
    public void getOwnQuizzesNoContent(){
        given().contentType(ContentType.JSON)
        .get("/quiz-fest/api/quizzes")
        .then()
        .statusCode(200);
    }

    @Test
    @TestSecurity(user = "")
    public void getOwnQuizzesNotLoggedIn(){
        given().contentType(ContentType.JSON)
        .get("/quiz-fest/api/quizzes")
        .then()
        .statusCode(401);
    }


}
