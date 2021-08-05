package de.os.hs.swa;


import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import de.os.hs.swa.category.entity.Category;
import de.os.hs.swa.quiz.control.DOTs.AnswerDTO;
import de.os.hs.swa.quiz.control.DOTs.QuestionDTO;
import de.os.hs.swa.quiz.control.DOTs.QuizEditDTO;
import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import io.restassured.http.ContentType;


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
