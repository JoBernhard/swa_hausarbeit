package de.os.hs.swa;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
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

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

@QuarkusTest
public class EditRessourceTest {

  private static String categoryName="Natur";
  private static String title = "Naturquiz";
  private static String questionTitle = "Was ist keine Zimmerpflanze?";
  private static String firtstAnswerText = "Baum";
  private static String secondAnswerText = "Aloe Vera";

  /*
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
  @TestTransaction
  @TestSecurity(user="theErstellerIn")
  public void editQuizOk(){
    Long quizId = 1L;
    ArrayList<AnswerDTO> answers = new ArrayList<>();
    answers.add(new AnswerDTO(firtstAnswerText, true));
    answers.add(new AnswerDTO(secondAnswerText, false));
    QuizEditDTO quiz = createQuiz(categoryName, "", createQuestion(answers));
    given().contentType(ContentType.JSON)
    .body(quiz).put("/quizzes/"+quizId+"/edit")
      .then().statusCode(200);
  }

  @Test    
  @TestTransaction
  @TestSecurity(user="theErstellerIn")
  public void editQuizNotFound(){
    Long quizId = 0L;
    ArrayList<AnswerDTO> answers = new ArrayList<>();
    answers.add(new AnswerDTO(firtstAnswerText, true));
    answers.add(new AnswerDTO(secondAnswerText, false));
    QuizEditDTO quiz = createQuiz(categoryName, "", createQuestion(answers));
    given().contentType(ContentType.JSON)
    .body(quiz).put("/quizzes/"+quizId+"/edit")
        .then().statusCode(404);
  }

  @Test
  @TestTransaction
  @TestSecurity(user="theSpielerIn")
  public void editQuizNotCreator(){
    Long quizId = 1L;
    ArrayList<AnswerDTO> answers = new ArrayList<>();
    answers.add(new AnswerDTO(firtstAnswerText, true));
    answers.add(new AnswerDTO(secondAnswerText, false));
    QuizEditDTO quiz = createQuiz(categoryName, "", createQuestion(answers));
    given().contentType(ContentType.JSON)
    .body(quiz).put("/quizzes/"+quizId+"/edit")
        .then().statusCode(403);
  }

  @Test
  @TestTransaction
  @TestSecurity(user="theErstellerIn")
  public void editQuizNoQuizTitle(){
    ArrayList<AnswerDTO> answers = new ArrayList<>();
    answers.add(new AnswerDTO(firtstAnswerText, true));
    answers.add(new AnswerDTO(secondAnswerText, false));
    QuizEditDTO quiz = createQuiz(categoryName, "", createQuestion(answers));
    Long quizId = 1L;
    given().contentType(ContentType.JSON)
    .body(quiz).put("/quizzes/"+quizId+"/edit")
      .then().statusCode(400);
  }

  @Test
  @TestTransaction
  @TestSecurity(user="theErstellerIn")
  public void editQuizNoQuestion(){
    QuizEditDTO quiz = createQuiz(categoryName, title, null);
    Long quizId = 1L;
    given().contentType(ContentType.JSON)
        .body(quiz)
        .put("/quizzes/"+quizId+"/edit")
        .then()
        .statusCode(400);
  }

  @Test
  @TestTransaction
  @TestSecurity(user="theErstellerIn")
    public void editQuizOneAnswer(){
      Long quizId = 1L;
        ArrayList<AnswerDTO> answers = new ArrayList<>();
        answers.add(new AnswerDTO(firtstAnswerText, true));
        QuizEditDTO quiz = createQuiz(categoryName, title, createQuestion(answers));
        given().contentType(ContentType.JSON)
        .body(quiz)
        .put("/quizzes/"+quizId+"/edit")
        .then()
        .statusCode(400);
    }

    @Test
    @TestTransaction
    @TestSecurity(user="theErstellerIn")
    public void editQuizNoCorrectAnswer(){
      Long quizId = 1L;
        ArrayList<AnswerDTO> answers = new ArrayList<>();
        answers.add(new AnswerDTO(firtstAnswerText, true));
        answers.add(new AnswerDTO("", false));
        QuizEditDTO quiz = createQuiz(categoryName, title, createQuestion(answers));
        given().contentType(ContentType.JSON)
        .body(quiz)
        .put("/quizzes/"+quizId+"/edit")
        .then()
        .statusCode(400);
    }

    @Test
    @TestTransaction
    @TestSecurity(user="theErstellerIn")
    public void editQuizInvalidAnswerText(){
      Long quizId = 1L;
      ArrayList<AnswerDTO> answers = new ArrayList<>();
      answers.add(new AnswerDTO(firtstAnswerText, true));
      answers.add(new AnswerDTO("", false));
      QuizEditDTO quiz = createQuiz(categoryName, title, createQuestion(answers));
      given().contentType(ContentType.JSON)
      .body(quiz)
      .put("/quizzes/"+quizId+"/edit")
      .then()
      .statusCode(400);
    }

    @Test
    @TestTransaction
    @TestSecurity(user="theErstellerIn")
    public void deleteQuizOk(){
      Long quizId = 1L;
      given().when().delete("/quizzes/"+quizId+"/edit")
      .then().statusCode(200);
    }

    @Test
    @TestTransaction
    @TestSecurity(user="theErstellerIn")
    public void getQuestionToEditOk(){
      Long quizId = 1L;
      int questionNr = 1;
      given().when().delete("/quizzes/"+quizId+"/edit/"+questionNr)
      .then().statusCode(200);
    }

    @Test
    @TestTransaction
    @TestSecurity(user="theErstellerIn")
    public void getQuestionToEditQuizNotFound(){
      Long quizId = 0L;
      int questionNr = 1;
      given().when().delete("/quizzes/"+quizId+"/edit/"+questionNr)
      .then().statusCode(404);
    }

    @Test
    @TestTransaction
    @TestSecurity(user="theErstellerIn")
    public void getQuestionToEditQuestionNotFound(){
      Long quizId = 1L;
      int questionNr = 5;
      given().when().delete("/quizzes/"+quizId+"/edit/"+questionNr)
      .then().statusCode(404);
    }

    @Test
    @TestTransaction
    @TestSecurity(user="theErstellerIn")
    public void editQuestionOk(){
      Long quizId = 1L;
      int questionNr = 1;
      ArrayList<AnswerDTO> answers = new ArrayList<>();
      answers.add(new AnswerDTO(firtstAnswerText, true));
      answers.add(new AnswerDTO(secondAnswerText, false));
      given().contentType(ContentType.JSON)
      .body(createQuestion(answers)).delete("/quizzes/"+quizId+"/edit/"+questionNr)
      .then().statusCode(404);
    }

    @Test
    @TestTransaction
    @TestSecurity(user="theErstellerIn")
    public void editQuestionQuizNotFound(){
      Long quizId = 0L;
      int questionNr = 1;
      String newTitle = "Was ist keine Sukkulente?";
      ArrayList<AnswerDTO> answers = new ArrayList<>();
      answers.add(new AnswerDTO(firtstAnswerText, true));
      answers.add(new AnswerDTO(secondAnswerText, false));
      QuestionDTO q = new QuestionDTO(newTitle, answers);

      given().contentType(ContentType.JSON)
      .body(q).delete("/quizzes/"+quizId+"/edit/"+questionNr)
      .then().statusCode(404);
    }

    @Test
    @TestTransaction
    @TestSecurity(user="theErstellerIn")
    public void editQuestionQuestionNrNotFound(){
      Long quizId = 0L;
      int questionNr = 5;
      String newTitle = "Was ist keine Sukkulente?";
      ArrayList<AnswerDTO> answers = new ArrayList<>();
      answers.add(new AnswerDTO(firtstAnswerText, true));
      answers.add(new AnswerDTO(secondAnswerText, false));
      QuestionDTO q = new QuestionDTO(newTitle,answers);

      given().contentType(ContentType.JSON)
      .body(q).put("/quizzes/"+quizId+"/edit/"+questionNr)
      .then().statusCode(404);
    }

    @Test
    @TestTransaction
    @TestSecurity(user="theSpielerIn")
    public void editQuestionNotCreator(){
      Long quizId = 0L;
      int questionNr = 5;
      String newTitle = "Was ist keine Sukkulente?";
      ArrayList<AnswerDTO> answers = new ArrayList<>();
      answers.add(new AnswerDTO(firtstAnswerText, true));
      answers.add(new AnswerDTO(secondAnswerText, false));
      QuestionDTO q = new QuestionDTO(newTitle, answers);

      given().contentType(ContentType.JSON)
      .body(q).put("/quizzes/"+quizId+"/edit/"+questionNr)
      .then().statusCode(403);
    }

    @Test
    @TestTransaction
    @TestSecurity(user="theErstellerIn")
    public void editQuestionTitleEmpty(){
      Long quizId = 0L;
      int questionNr = 5;
      String newTitle = "";
      ArrayList<AnswerDTO> answers = new ArrayList<>();
      answers.add(new AnswerDTO(firtstAnswerText, true));
      answers.add(new AnswerDTO(secondAnswerText, false));
      QuestionDTO q = new QuestionDTO(newTitle, answers);

      given().contentType(ContentType.JSON)
      .body(q).put("/quizzes/"+quizId+"/edit/"+questionNr)
      .then().statusCode(400);
    }

    @Test
    @TestTransaction
    @TestSecurity(user="theErstellerIn")
    public void editQuestionAnswerTextEmpty(){
      Long quizId = 0L;
      int questionNr = 5;
      String newTitle = "";
      ArrayList<AnswerDTO> answers = new ArrayList<>();
      answers.add(new AnswerDTO("",true));
      answers.add(new AnswerDTO(secondAnswerText, false));
      QuestionDTO q = new QuestionDTO(newTitle,answers);

      given().contentType(ContentType.JSON)
      .body(q).put("/quizzes/"+quizId+"/edit/"+questionNr)
      .then().statusCode(400);
    }

    @Test
    @TestTransaction
    @TestSecurity(user="theErstellerIn")
    public void editQuestionOneAnswer(){
      Long quizId = 0L;
      int questionNr = 5;
      String newTitle = "";
      ArrayList<AnswerDTO> answers = new ArrayList<>();
      answers.add(new AnswerDTO(firtstAnswerText,true));
      QuestionDTO q = new QuestionDTO(newTitle,answers);

      given().contentType(ContentType.JSON)
      .body(q).put("/quizzes/"+quizId+"/edit/"+questionNr)
      .then().statusCode(400);
    }

    @Test
    @TestTransaction
    @TestSecurity(user="theErstellerIn")
    public void editQuestionNoCorrectAnswer(){
      Long quizId = 0L;
      int questionNr = 5;
      String newTitle = "";
      ArrayList<AnswerDTO> answers = new ArrayList<>();
      answers.add(new AnswerDTO(firtstAnswerText, true));
      QuestionDTO q = new QuestionDTO(newTitle, answers);

      given().contentType(ContentType.JSON)
      .body(q).put("/quizzes/"+quizId+"/edit/"+questionNr)
      .then().statusCode(400);
    }

  public QuestionDTO createQuestion(Collection<AnswerDTO> answers){
    return new QuestionDTO(questionTitle,answers);
  }

  
  public QuizEditDTO createQuiz(String categoryName, String title, QuestionDTO question){
        
    ArrayList<QuestionDTO> questions = new ArrayList<QuestionDTO>();
    questions.add(question);
    Category c = new Category();
    c.setName(categoryName);
    return new QuizEditDTO(c, title, questions);
}*/



}
