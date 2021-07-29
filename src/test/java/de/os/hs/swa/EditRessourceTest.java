package de.os.hs.swa;
import org.junit.jupiter.api.Test;

import de.os.hs.swa.quiz.control.AnswerDTO;
import de.os.hs.swa.quiz.control.QuestionDTO;
import de.os.hs.swa.quiz.control.QuizEditDTO;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.Collection;

@QuarkusTest
public class EditRessourceTest {

  private static String categoryName="Natur";
  private static String title = "Naturquiz";
  private static String questionTitle = "Was ist keine Zimmerpflanze?";
  private static String firtstAnswerText = "Baum";
  private static String secondAnswerText = "Aloe Vera";

  @Test
  public void getQuizOk(){
      Long quizId = 1L;
      given()
        .when().get("/quizzes/"+quizId+"/edit")
        .then().statusCode(200);
  }

  @Test
  @TestSecurity(user="theErstellerIn")
  public void getQuizNotFound(){
      Long quizId = 0L;
      given()
        .when().get("/quizzes/"+quizId+"/edit")
        .then().statusCode(404);
  }

  @Test    
  @TestSecurity(user="theErstellerIn")
  public void editQuizOk(){
      Long quizId = 1L;
      given()
        .when().put("/quizzes/"+quizId+"/edit")
        .then().statusCode(200);
  }

  @Test    
  @TestSecurity(user="theErstellerIn")
  public void editQuizNotFound(){
      Long quizId = 0L;
      given()
        .when().put("/quizzes/"+quizId+"/edit")
        .then().statusCode(404);
  }

  @Test
  @TestSecurity(user="theSpielerIn")
  public void editQuizNotCreator(){
      Long quizId = 1L;
      given()
        .when().put("/quizzes/"+quizId+"/edit")
        .then().statusCode(403);
  }

  @Test
  @TestSecurity(user="theErstellerIn")
  public void editQuizNoQuizTitle(){
    ArrayList<AnswerDTO> answers = new ArrayList<>();
        answers.add(new AnswerDTO(firtstAnswerText, 1, true));
        answers.add(new AnswerDTO(secondAnswerText, 2, false));
        QuizEditDTO quiz = createQuiz(categoryName, "", createQuestion(answers));
      Long quizId = 1L;
      given().contentType(ContentType.JSON)
      .body(quiz).put("/quizzes/"+quizId+"/edit")
        .then().statusCode(400);
  }

  @Test
  @TestSecurity(user="theErstellerIn")
  public void editQuizNoQuestion(){
    QuizEditDTO quiz = createQuiz(categoryName, title, null);
    Long quizId = 1L;
    given().contentType(ContentType.JSON)
        .body(quiz)
        .put("/quizzes"+quizId+"/edit")
        .then()
        .statusCode(400);
  }

  @Test
  @TestSecurity(user="theErstellerIn")
    public void editQuizOneAnswer(){
      Long quizId = 1L;
        ArrayList<AnswerDTO> answers = new ArrayList<>();
        answers.add(new AnswerDTO(firtstAnswerText, 1, true));
        QuizEditDTO quiz = createQuiz(categoryName, title, createQuestion(answers));
        given().contentType(ContentType.JSON)
        .body(quiz)
        .put("/quizzes"+quizId+"/edit")
        .then()
        .statusCode(400);
    }

    @Test
    @TestSecurity(user="theErstellerIn")
    public void editQuizNoCorrectAnswer(){
      Long quizId = 1L;
        ArrayList<AnswerDTO> answers = new ArrayList<>();
        answers.add(new AnswerDTO(firtstAnswerText, 1, true));
        answers.add(new AnswerDTO("", 2, false));
        QuizEditDTO quiz = createQuiz(categoryName, title, createQuestion(answers));
        given().contentType(ContentType.JSON)
        .body(quiz)
        .put("/quizzes"+quizId+"/edit")
        .then()
        .statusCode(400);
    }

    @Test
    @TestSecurity(user="theErstellerIn")
    public void createQuizInvalidAnswerText(){
      Long quizId = 1L;
        ArrayList<AnswerDTO> answers = new ArrayList<>();
        answers.add(new AnswerDTO(firtstAnswerText, 1, true));
        answers.add(new AnswerDTO("", 2, false));
        QuizEditDTO quiz = createQuiz(categoryName, title, createQuestion(answers));
        given().contentType(ContentType.JSON)
        .body(quiz)
        .put("/quizzes"+quizId+"/edit")
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



}
