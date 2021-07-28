package de.os.hs.swa;

import org.junit.jupiter.api.Test;

import de.os.hs.swa.quiz.control.QuizEditDTO;
import de.os.hs.swa.quiz.entity.Answer;
import de.os.hs.swa.quiz.entity.Question;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

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

    @Test
    public void createQuizOk(){
        ArrayList<Answer> answers = new ArrayList<>();
        answers.add(new Answer(firtstAnswerText, 1, true));
        answers.add(new Answer(secondAnswerText, 2, false));
        QuizEditDTO quiz = createQuiz(categoryName, title, createQuestion(answers));
        given().contentType(ContentType.JSON)
        .body(quiz)
        .post("/quizzes")
        .then()
        .statusCode(201);
    }

    @Test
    public void createQuizNoTitle(){
        ArrayList<Answer> answers = new ArrayList<>();
        answers.add(new Answer(firtstAnswerText, 1, true));
        answers.add(new Answer(secondAnswerText, 2, false));
        QuizEditDTO quiz = createQuiz(categoryName, "", createQuestion(answers));
        given().contentType(ContentType.JSON)
        .body(quiz)
        .post("/quizzes")
        .then()
        .statusCode(400);
    }

    @Test
    public void createQuizInvalidCategory(){
        ArrayList<Answer> answers = new ArrayList<>();
        answers.add(new Answer(firtstAnswerText, 1, true));
        answers.add(new Answer(secondAnswerText, 2, false));
        QuizEditDTO quiz = createQuiz("", title, createQuestion(answers) );
        given().contentType(ContentType.JSON)
        .body(quiz)
        .post("/quizzes")
        .then()
        .statusCode(400);
    }
    
    @Test
    public void createQuizOneAnswer(){
        
        ArrayList<Answer> answers = new ArrayList<>();
        answers.add(new Answer(firtstAnswerText, 1, true));
        QuizEditDTO quiz = createQuiz(categoryName, title, createQuestion(answers));
        given().contentType(ContentType.JSON)
        .body(quiz)
        .post("/quizzes")
        .then()
        .statusCode(400);
    }

    @Test
    public void createQuizOneNoQuestion(){
        QuizEditDTO quiz = createQuiz(categoryName, title, null);
        given().contentType(ContentType.JSON)
        .body(quiz)
        .post("/quizzes")
        .then()
        .statusCode(400);
    }

    @Test
    public void createQuizInvalidAnswerText(){
        ArrayList<Answer> answers = new ArrayList<>();
        answers.add(new Answer(firtstAnswerText, 1, true));
        answers.add(new Answer("", 2, false));
        QuizEditDTO quiz = createQuiz(categoryName, title, createQuestion(answers));
        given().contentType(ContentType.JSON)
        .body(quiz)
        .post("/quizzes")
        .then()
        .statusCode(400);
    }

    @Test
    public void createQuizInvalidNoCorrectAnswer(){
        ArrayList<Answer> answers = new ArrayList<>();
        answers.add(new Answer(firtstAnswerText, 1, true));
        answers.add(new Answer("", 2, false));
        QuizEditDTO quiz = createQuiz(categoryName, title, createQuestion(answers));
        given().contentType(ContentType.JSON)
        .body(quiz)
        .post("/quizzes")
        .then()
        .statusCode(400);
    }

    public Question createQuestion(Collection<Answer> answers){
        return new Question(questionTitle, 1, answers);
    }

    public QuizEditDTO createQuiz(String categoryName, String title, Question question){
        
        ArrayList<Question> questions = new ArrayList<Question>();
        questions.add(question);
        return new QuizEditDTO(categoryName, title, questions);
    }
}
