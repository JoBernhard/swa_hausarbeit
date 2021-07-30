package de.os.hs.swa.quiz.boundary;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import de.os.hs.swa.quiz.control.PlayQuestionDTO;
import de.os.hs.swa.quiz.control.PlayService;
import de.os.hs.swa.quiz.control.ResultDTO;

@Path("/quizzes/{quizID}/play/{questionNr}")
@Tag(name= "Play Quiz")
public class PlayRessource {


    @Inject
    PlayService playService;

    @GET
    @Operation(description = "get question in a playable format")
    public PlayQuestionDTO playQuestion(@PathParam("quizID") Long quizID, @PathParam("questionNr") int QuestionNr){
        return playService.getQuestion(quizID, QuestionNr);
    }

    @Transactional
    @POST
    @Path("/{answerIndex}")
    @Operation(description = "answer question, returns response with given points and correct answer as well as link to next question")
    public ResultDTO answerQuestion(@PathParam("quizID") Long quizID, @PathParam("questionNr") int QuestionNr, @PathParam("answerIndex") int choosenAnswer){
        return playService.answerQuestion(quizID, QuestionNr, choosenAnswer);
    }
}
