package de.os.hs.swa.quiz.boundary;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import de.os.hs.swa.quiz.entity.Question;

@Path("/quizzes/{quizID}/edit")
public class EditRessource {
    @GET
    @Path("/{questionNr}")
    public Question getQuestionByNumber(@PathParam("quizID") Long quizID, @PathParam("questionNr") int questionNr){
        return null;
    }

    @POST
    public Question addQuestionToQuiz(@PathParam("quizID") Long quizID, Question question){
        return null;
    }

    @GET
    @Path("/{questionNr}")
    public Question editQuestionByNumber(@PathParam("quizID") Long quizID, @PathParam("questionNr") int questionNr, Question question){
        return null;
    }

    @DELETE
    @Path("/{questionNr}")
    public Question removeQuestionByNumber(@PathParam("quizID") Long quizID, @PathParam("questionNr") int questionNr){
        return null;
    }
}
