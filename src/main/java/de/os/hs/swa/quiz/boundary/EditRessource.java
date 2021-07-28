package de.os.hs.swa.quiz.boundary;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import de.os.hs.swa.quiz.entity.Question;

@Path("/quizzes/{quizID}/edit/{questionNr}")
@Tag(name = "Edit Qustion")
public class EditRessource {
    @GET
    public Question getQuestionByNumber(@PathParam("quizID") Long quizID, @PathParam("questionNr") int questionNr){
        return null;
    }

    @PUT
    public Question editQuestionByNumber(@PathParam("quizID") Long quizID, @PathParam("questionNr") int questionNr, Question question){
        return null;
    }

    @DELETE
    public Question removeQuestionByNumber(@PathParam("quizID") Long quizID, @PathParam("questionNr") int questionNr){
        return null;
    }
}
