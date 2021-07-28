package de.os.hs.swa;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import de.os.hs.swa.quiz.entity.Answer;
import de.os.hs.swa.quiz.entity.Question;
import de.os.hs.swa.quiz.entity.Quiz;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;



// @author: Laura Peter
@QuarkusTest 
public class QuizRessourceTest {

    private static String validCatigoryname="Natur";
    private static String validTitle = "Naturquiz";
    private static String validQuestion = "Was ist keine Zimmerpflanze?";
    private static String validFirstAnswer = "Baum";
    private static String validSecondAnswer = "Aloe Vera";

    @Test
    public void createQuizOk(){
        String quizString = createQuizString(validCatigoryname, validTitle, validQuestion, new Answer(validFirstAnswer, 1, true), new Answer(validFirstAnswer, 2, true));
        given().body(quizString).contentType(ContentType.JSON)
        .when().post("/quizzes").then().statusCode(201);
    }

    public String createQuizString(String categoryName, String title, String questionText, Answer answer1, Answer answer2){
        ArrayList<Answer> answers = new ArrayList<Answer>();
        answers.add(answer1);
        answers.add(answer2);
        Question question = new Question(questionText, 1, answers);
        
        JsonBuilderFactory factory = Json.createBuilderFactory(null);
        JsonObject jsonObject = Json.createObjectBuilder()
        .add("title", title)
        .add("questions", factory.createArrayBuilder()
            .add(factory.createObjectBuilder()
                .add("nr",1)
                .add("text", question.getText())
                .add("answers", factory.createArrayBuilder()
                    .add(factory.createObjectBuilder()
                        .add("text", answer1.getText())
                        .add("answerNr", answer1.getAnswerNr())
                        .add("isCorrect",answer1.getIsCorrect()))
                    .add(factory.createObjectBuilder()
                        .add("text", answer2.getText())
                        .add("answerNr", answer2.getAnswerNr())
                        .add("isCorrect",answer2.getIsCorrect())
                        ))))
        .build();

        return jsonObject.toString();
    }
}
