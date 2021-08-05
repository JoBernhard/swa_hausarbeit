package de.os.hs.swa.quiz.boundary;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import de.os.hs.swa.quiz.control.PlayService;
import de.os.hs.swa.quiz.control.DOTs.PlayQuestionDTO;
import de.os.hs.swa.quiz.control.DOTs.ResultDTO;


//@author: Johanna Bernhard
@Path("/quizzes/{quizID}/play")
@Tag(name= "Play Quiz")
public class PlayRessource {

    @Inject
    PlayService playService;

    @GET
    @Operation(description = "gets the first question of quiz with id")
    public PlayQuestionDTO playQuiz(@Parameter(example = "100") @PathParam("quizID") Long quizID){
        return playService.chooseQuiz(quizID);
    }

    @GET
    @Path("/{questionNr}")
    @Operation(description = "get question in a playable format")
    public PlayQuestionDTO playQuestion(@Parameter(example = "100") @PathParam("quizID") Long quizID,@Parameter(example = "1") @PathParam("questionNr") int QuestionNr){
        return playService.getQuestion(quizID, QuestionNr);
    }

    @Transactional
    @POST
    @Path("/{questionNr}/{answerIndex}")
    @Operation(description = "answer question, returns response with given points and correct answer as well as link to next question")
    public ResultDTO answerQuestion(@Parameter(example = "100") @PathParam("quizID") Long quizID,@Parameter(example = "1") @PathParam("questionNr") int QuestionNr,@Parameter(example = "1") @PathParam("answerIndex") int choosenAnswer){
        return playService.answerQuestion(quizID, QuestionNr, choosenAnswer);
    }
}
