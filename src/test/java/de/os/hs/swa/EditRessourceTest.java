package de.os.hs.swa;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.postgresql.copy.CopyManager;
import org.postgresql.core.BaseConnection;

import de.os.hs.swa.category.entity.Category;
import de.os.hs.swa.quiz.control.DOTs.AnswerDTO;
import de.os.hs.swa.quiz.control.DOTs.QuestionDTO;
import de.os.hs.swa.quiz.control.DOTs.QuizEditDTO;
import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

@QuarkusTest
public class EditRessourceTest {

  private static String categoryName="Natur";
  private static String title = "Neuer Titel";
  private static String questionTitle = "Was ist die bessere Antwort?";
  private static String firtstAnswerText = "neue Antwort1";
  private static String secondAnswerText = "neue Antwort2";

  @BeforeAll
  public static void setup(){

  }

  @Test 
  @TestSecurity(user="laupeter")
  public void getQuizOk(){
      Long quizId = 200L;
      given()
        .when().get("/quiz-fest/api/quizzes/"+quizId+"/edit")
        .then().statusCode(200);
  }

  @Test
  @TestSecurity(user="laupeter")
  public void getQuizNotFound(){
      Long quizId = 0L;
      given()
        .when().get("/quiz-fest/api/quizzes/"+quizId+"/edit")
        .then().statusCode(404);
  }

  
  @Test 
  @TestTransaction
  @TestSecurity(user="laupeter")
  public void editQuizOk(){
    Long quizId = 200L;
    ArrayList<AnswerDTO> answers = new ArrayList<>();
    answers.add(new AnswerDTO(firtstAnswerText, true));
    answers.add(new AnswerDTO(secondAnswerText, false));
    QuizEditDTO quiz = createQuiz(categoryName, title, createQuestion(answers));
    given().contentType(ContentType.JSON)
    .body(quiz).put("/quiz-fest/api/quizzes/"+quizId+"/edit")
      .then().statusCode(200);
  }
  
  @Test    
  @TestTransaction
  @TestSecurity(user="laupeter")
  public void editQuizNotFound(){
    Long quizId = 0L;
    ArrayList<AnswerDTO> answers = new ArrayList<>();
    answers.add(new AnswerDTO(firtstAnswerText, true));
    answers.add(new AnswerDTO(secondAnswerText, false));
    QuizEditDTO quiz = createQuiz(categoryName, title, createQuestion(answers));
    given().contentType(ContentType.JSON)
    .body(quiz).put("/quiz-fest/api/quizzes/"+quizId+"/edit")
        .then().statusCode(404);
  }

  @Test
  @TestTransaction
  @TestSecurity(user="jobernhard")
  public void editQuizNotCreator(){
    Long quizId = 200L;
    ArrayList<AnswerDTO> answers = new ArrayList<>();
    answers.add(new AnswerDTO(firtstAnswerText, true));
    answers.add(new AnswerDTO(secondAnswerText, false));
    QuizEditDTO quiz = createQuiz(categoryName, title, createQuestion(answers));
    given().contentType(ContentType.JSON)
    .body(quiz).put("/quiz-fest/api/quizzes/"+quizId+"/edit")
        .then().statusCode(403);
  }

  @Test
  @TestTransaction
  @TestSecurity(user="laupeter")
  public void editQuizNoQuizTitle(){
    ArrayList<AnswerDTO> answers = new ArrayList<>();
    answers.add(new AnswerDTO(firtstAnswerText, true));
    answers.add(new AnswerDTO(secondAnswerText, false));
    QuizEditDTO quiz = createQuiz(categoryName, "", createQuestion(answers));
    Long quizId = 200L;
    given().contentType(ContentType.JSON)
    .body(quiz).put("/quiz-fest/api/quizzes/"+quizId+"/edit")
      .then().statusCode(400);
  }

  @Test
  @TestTransaction
  @TestSecurity(user="laupeter")
  public void editQuizNoQuestion(){
    QuizEditDTO quiz = createQuiz(categoryName, title, null);
    Long quizId = 200L;
    given().contentType(ContentType.JSON)
        .body(quiz)
        .put("/quiz-fest/api/quizzes/"+quizId+"/edit")
        .then()
        .statusCode(400);
  }

  @Test
  @TestTransaction
  @TestSecurity(user="laupeter")
    public void editQuizOneAnswer(){
      Long quizId = 200L;
        ArrayList<AnswerDTO> answers = new ArrayList<>();
        answers.add(new AnswerDTO(firtstAnswerText, true));
        QuizEditDTO quiz = createQuiz(categoryName, title, createQuestion(answers));
        given().contentType(ContentType.JSON)
        .body(quiz)
        .put("/quiz-fest/api/quizzes/"+quizId+"/edit")
        .then()
        .statusCode(400);
    }

    @Test
    @TestTransaction
    @TestSecurity(user="laupeter")
    public void editQuizNoCorrectAnswer(){
      Long quizId = 200L;
        ArrayList<AnswerDTO> answers = new ArrayList<>();
        answers.add(new AnswerDTO(firtstAnswerText, true));
        answers.add(new AnswerDTO("", false));
        QuizEditDTO quiz = createQuiz(categoryName, title, createQuestion(answers));
        given().contentType(ContentType.JSON)
        .body(quiz)
        .put("/quiz-fest/api/quizzes/"+quizId+"/edit")
        .then()
        .statusCode(400);
    }

    @Test
    @TestTransaction
    @TestSecurity(user="laupeter")
    public void editQuizInvalidAnswerText(){
      Long quizId = 200L;
      ArrayList<AnswerDTO> answers = new ArrayList<>();
      answers.add(new AnswerDTO(firtstAnswerText, true));
      answers.add(new AnswerDTO("", false));
      QuizEditDTO quiz = createQuiz(categoryName, title, createQuestion(answers));
      given().contentType(ContentType.JSON)
      .body(quiz)
      .put("/quiz-fest/api/quizzes/"+quizId+"/edit")
      .then()
      .statusCode(400);
    }

    

    @Test
    @TestTransaction
    @TestSecurity(user="laupeter")
    public void getQuestionToEditOk(){
      Long quizId = 200L;
      int questionNr = 1;
      given().when().get("/quiz-fest/api/quizzes/"+quizId+"/edit/"+questionNr)
      .then().statusCode(200);
    }

    @Test
    @TestTransaction
    @TestSecurity(user="laupeter")
    public void getQuestionToEditQuizNotFound(){
      Long quizId = 0L;
      int questionNr = 1;
      given().when().get("/quiz-fest/api/quizzes/"+quizId+"/edit/"+questionNr)
      .then().statusCode(404);
    }

    @Test
    @TestTransaction
    @TestSecurity(user="laupeter")
    public void getQuestionToEditQuestionNotFound(){
      Long quizId = 200L;
      int questionNr = 5;
      given().when().get("/quiz-fest/api/quizzes/"+quizId+"/edit/"+questionNr)
      .then().statusCode(404);
    }

    @Test
    @TestTransaction
    @TestSecurity(user="laupeter")
    public void editQuestionOk(){
      Long quizId = 200L;
      int questionNr = 1;
      ArrayList<AnswerDTO> answers = new ArrayList<>();
      answers.add(new AnswerDTO(firtstAnswerText, true));
      answers.add(new AnswerDTO(secondAnswerText, false));
      QuestionDTO q = new QuestionDTO(questionTitle, answers);
      given().contentType(ContentType.JSON)
      .body(q).put("/quiz-fest/api/quizzes/"+quizId+"/edit/"+questionNr)
      .then().statusCode(200);
    }

    @Test
    @TestTransaction
    @TestSecurity(user="laupeter")
    public void editQuestionQuizNotFound(){
      Long quizId = 0L;
      int questionNr = 1;
      ArrayList<AnswerDTO> answers = new ArrayList<>();
      answers.add(new AnswerDTO(firtstAnswerText, true));
      answers.add(new AnswerDTO(secondAnswerText, false));
      QuestionDTO q = new QuestionDTO(questionTitle, answers);

      given().contentType(ContentType.JSON)
      .body(q).put("/quiz-fest/api/quizzes/"+quizId+"/edit/"+questionNr)
      .then().statusCode(404);
    }

    @Test
    @TestTransaction
    @TestSecurity(user="laupeter")
    public void editQuestionQuestionNrNotFound(){
      Long quizId = 200L;
      int questionNr = 5;
      ArrayList<AnswerDTO> answers = new ArrayList<>();
      answers.add(new AnswerDTO(firtstAnswerText, true));
      answers.add(new AnswerDTO(secondAnswerText, false));
      QuestionDTO q = new QuestionDTO(questionTitle,answers);

      given().contentType(ContentType.JSON)
      .body(q).put("/quiz-fest/api/quizzes/"+quizId+"/edit/"+questionNr)
      .then().statusCode(404);
    }

    @Test
    @TestTransaction
    @TestSecurity(user="jobernhard")
    public void editQuestionNotCreator(){
      Long quizId = 200L;
      int questionNr = 1;
      String newTitle = "Was ist keine Sukkulente?";
      ArrayList<AnswerDTO> answers = new ArrayList<>();
      answers.add(new AnswerDTO(firtstAnswerText, true));
      answers.add(new AnswerDTO(secondAnswerText, false));
      QuestionDTO q = new QuestionDTO(newTitle, answers);

      given().contentType(ContentType.JSON)
      .body(q).put("/quiz-fest/api/quizzes/"+quizId+"/edit/"+questionNr)
      .then().statusCode(403);
    }

    @Test
    @TestTransaction
    @TestSecurity(user="laupeter")
    public void editQuestionTitleEmpty(){
      Long quizId = 200L;
      int questionNr = 1;
      String newTitle = "";
      ArrayList<AnswerDTO> answers = new ArrayList<>();
      answers.add(new AnswerDTO(firtstAnswerText, true));
      answers.add(new AnswerDTO(secondAnswerText, false));
      QuestionDTO q = new QuestionDTO(newTitle, answers);

      given().contentType(ContentType.JSON)
      .body(q).put("/quiz-fest/api/quizzes/"+quizId+"/edit/"+questionNr)
      .then().statusCode(400);
    }

    @Test
    @TestTransaction
    @TestSecurity(user="laupeter")
    public void editQuestionAnswerTextEmpty(){
      Long quizId = 200L;
      int questionNr = 1;
      ArrayList<AnswerDTO> answers = new ArrayList<>();
      answers.add(new AnswerDTO("",true));
      answers.add(new AnswerDTO(secondAnswerText, false));
      QuestionDTO q = new QuestionDTO(questionTitle,answers);

      given().contentType(ContentType.JSON)
      .body(q).put("/quiz-fest/api/quizzes/"+quizId+"/edit/"+questionNr)
      .then().statusCode(400);
    }

    @Test
    @TestTransaction
    @TestSecurity(user="laupeter")
    public void editQuestionOneAnswer(){
      Long quizId = 200L;
      int questionNr = 1;
      ArrayList<AnswerDTO> answers = new ArrayList<>();
      answers.add(new AnswerDTO(firtstAnswerText,true));
      QuestionDTO q = new QuestionDTO(questionTitle,answers);

      given().contentType(ContentType.JSON)
      .body(q).put("/quiz-fest/api/quizzes/"+quizId+"/edit/"+questionNr)
      .then().statusCode(400);
    }

    @Test
    @TestTransaction
    @TestSecurity(user="laupeter")
    public void editQuestionNoCorrectAnswer(){
      Long quizId = 200L;
      int questionNr = 1;
      ArrayList<AnswerDTO> answers = new ArrayList<>();
      answers.add(new AnswerDTO(firtstAnswerText, false));
      answers.add(new AnswerDTO(secondAnswerText, false));
      QuestionDTO q = new QuestionDTO(questionTitle, answers);

      given().contentType(ContentType.JSON)
      .body(q).put("/quiz-fest/api/quizzes/"+quizId+"/edit/"+questionNr)
      .then().statusCode(400);
    }

    @Test
    @TestTransaction
    @TestSecurity(user="jobernhard")
    public void deleteQuizOk(){
      Long quizId = 100L;
      given().when().delete("/quiz-fest/api/quizzes/"+quizId+"/edit")
      .then().statusCode(204);
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



}
