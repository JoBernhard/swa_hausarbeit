package de.os.hs.swa;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.postgresql.copy.CopyManager;
import org.postgresql.core.BaseConnection;

import de.os.hs.swa.quiz.control.DOTs.AnswerDTO;
import de.os.hs.swa.quiz.control.DOTs.QuestionDTO;
import de.os.hs.swa.quiz.control.DOTs.QuizEditDTO;
import de.os.hs.swa.quiz.entity.Answer;
import de.os.hs.swa.quiz.entity.Question;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

// @author: Laura Peter
@QuarkusTest @TestSecurity(authorizationEnabled = false)
public class QuizRessourceTest {

    private static String categoryName="Natur";
    private static String title = "Naturquiz";
    private static String questionTitle = "Was ist keine Zimmerpflanze?";
    private static String firtstAnswerText = "Baum";
    private static String secondAnswerText = "Aloe Vera";
/*
    @BeforeAll
    public static void init(){
        try {
            copyIntoTestDB("Category", "./testcategories.csv");
            copyIntoTestDB("QuizUser", "./testuser.csv");
            copyIntoTestDB("Quiz", "./testquiz.csv");
            copyIntoTestDB("Question", "./testquestion.csv");
            copyIntoTestDB("Answer", "./testanswer.csv");
        } catch (Exception e) {
            e.printStackTrace();
        } 
       
    }*/

    private static void copyIntoTestDB(String tablename, String file){
        try {
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/testdb","postgres","annie_box");
            long rowsInserted = new CopyManager((BaseConnection) con)
            .copyIn("COPY "+tablename+" FROM STDIN (FORMAT csv, HEADER)", new FileReader(file));
            System.out.printf("%d row(s) inserted%n", rowsInserted);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Test
    public void createQuizOk(){
        ArrayList<AnswerDTO> answers = new ArrayList<>();
        answers.add(new AnswerDTO(firtstAnswerText, 1, true));
        answers.add(new AnswerDTO(secondAnswerText, 2, false));
        QuizEditDTO quiz = createQuiz(categoryName, title, createQuestion(answers));
        given().contentType(ContentType.JSON)
        .body(quiz)
        .post("/quizzes")
        .then()
        .statusCode(201);
    }

    @Test
    public void createQuizNoTitle(){
        ArrayList<AnswerDTO> answers = new ArrayList<>();
        answers.add(new AnswerDTO(firtstAnswerText, 1, true));
        answers.add(new AnswerDTO(secondAnswerText, 2, false));
        QuizEditDTO quiz = createQuiz(categoryName, "", createQuestion(answers));
        given().contentType(ContentType.JSON)
        .body(quiz)
        .post("/quizzes")
        .then()
        .statusCode(400);
    }

    @Test
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
    public void createQuizNoQuestion(){
        QuizEditDTO quiz = createQuiz(categoryName, title, null);
        given().contentType(ContentType.JSON)
        .body(quiz)
        .post("/quizzes")
        .then()
        .statusCode(400);
    }

    @Test
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
    }

    public QuestionDTO createQuestion(Collection<AnswerDTO> answers){
        return new QuestionDTO(questionTitle, 1, answers);
    }

    public QuizEditDTO createQuiz(String categoryName, String title, QuestionDTO question){
        
        ArrayList<QuestionDTO> questions = new ArrayList<QuestionDTO>();
        questions.add(question);
        return new QuizEditDTO(categoryName, title, questions);
    }


    @Test
    @TestSecurity(user = "theErstellerIn")
    public void getOwnQuizzesOk(){
        int userId = 1;
        given().contentType(ContentType.JSON)
        .get("/quizzes")
        .then()
        .statusCode(200);
    }

    @Test
    @TestSecurity(user = "theSpielerIn")
    public void getOwnQuizzesNoContent(){
        int userId = 1;
        given().contentType(ContentType.JSON)
        .get("/quizzes")
        .then()
        .statusCode(204);
    }

    @Test
    @TestSecurity(user = "")
    public void getOwnQuizzesNotLoggedIn(){
        int userId = 1;
        given().contentType(ContentType.JSON)
        .get("/quizzes")
        .then()
        .statusCode(403);
    }


}
