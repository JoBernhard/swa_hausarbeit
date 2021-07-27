package de.os.hs.swa.quiz.boundary;

import java.util.Collection;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import de.os.hs.swa.quiz.control.QuizzListDTO;
import de.os.hs.swa.quiz.entity.Quiz;

@Path("/quizzes")
public class QuizzesRessource {
    @GET
    public Collection<QuizzListDTO> getOwnQuizzes(){
        return null;
    }

    @Path("{quizID}")
    @GET
    public Quiz getQuizByID(@PathParam("quizID") Long quizID){
        return null;
    }

    @Path("{quizID}")
    @PUT
    public Quiz editQuiz(@PathParam("quizID") Long quizID){
        return null;
    }

    @Path("{quizID}")
    @DELETE
    public void deletQuizByID(@PathParam("quizID") Long quizID){
        
    }
}
