package de.os.hs.swa.quiz.boundary;

import java.util.Collection;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import de.os.hs.swa.quiz.control.QuizListDTO;
import de.os.hs.swa.quiz.entity.Question;
import de.os.hs.swa.quiz.entity.Quiz;

@Path("/quizzes")
public class QuizzesRessource {
    @GET
    public Collection<QuizListDTO> getOwnQuizzes(){
        return null;
    }

    @POST
    public Quiz createNewQuiz(Quiz quiz){
        return null;
    }

    @Path("{quizID}/edit")
    @GET
    public Quiz getQuizByID(@PathParam("quizID") Long quizID){
        return null;
    }

    @Path("{quizID}/edit")
    @POST
    public Question addQuestionToQuiz(@PathParam("quizID") Long quizID, Question question){
        return null;
    }

    @Path("{quizID}/edit")
    @PUT
    public Quiz editQuiz(@PathParam("quizID") Long quizID){
        return null;
    }

    @Path("{quizID}/edit")
    @DELETE
    public void deletQuizByID(@PathParam("quizID") Long quizID){
        
    }
}
