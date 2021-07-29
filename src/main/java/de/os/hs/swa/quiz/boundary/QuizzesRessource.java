package de.os.hs.swa.quiz.boundary;

import java.util.Collection;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import de.os.hs.swa.quiz.control.QuizEditDTO;
import de.os.hs.swa.quiz.control.QuizListDTO;
import de.os.hs.swa.quiz.entity.Question;
import de.os.hs.swa.quiz.entity.Quiz;

@Path("/quizzes")
@Tag(name = "Own Quizzes")
public class QuizzesRessource {
    @GET
    @Operation(description = "get the list of own created quizzes")
    public Collection<QuizListDTO> getOwnQuizzes(){
        return null;
    }

    @POST
    @Operation(description = "create a new Quiz in a category")
    public Quiz createNewQuiz(QuizEditDTO quiz){
        return null;
    }

    @Path("{quizID}/edit")
    @GET
    @Operation(description = "get created quiz by id to edit")
    public Quiz getQuizByID(@PathParam("quizID") Long quizID){
        return null;
    }

    @Path("{quizID}/edit")
    @POST
    @Operation(description = "add new Question to quiz allowed for creator")
    public Question addQuestionToQuiz(@PathParam("quizID") Long quizID, Question question){
        return null;
    }

    @Path("{quizID}/edit")
    @PUT
    @Operation(description = "override quiz with given id with new quiz only for creator, returns the edited quiz")
    public Quiz editQuiz(@PathParam("quizID") Long quizID, QuizEditDTO quiz){
        return null;
    }

    @Path("{quizID}/edit")
    @DELETE
    @Operation(description = "delete quiz with id only for creator")
    public void deletQuizByID(@PathParam("quizID") Long quizID){
        
    }
}
