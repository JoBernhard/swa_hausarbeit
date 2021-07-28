package de.os.hs.swa.quiz.boundary;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import de.os.hs.swa.quiz.control.PlayQuestionDTO;
import de.os.hs.swa.quiz.control.ResultDTO;

@Path("/quizzes/{quizID}/play/{questionNr}")
public class PlayRessource {
    @GET
    public PlayQuestionDTO playQuestion(@PathParam("quizID") Long quizID, @PathParam("questionNr") int QuestionNr){
        return null;
    }

    @POST
    @Path("/{answerIndex}")
    public ResultDTO answerQuestion(@PathParam("quizID") Long quizID, @PathParam("questionNr") int QuestionNr, @PathParam("answerIndex") int choosenAnswer){
        return null;
    }
}
