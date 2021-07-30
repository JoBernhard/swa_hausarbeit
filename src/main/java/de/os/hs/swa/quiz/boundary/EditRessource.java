package de.os.hs.swa.quiz.boundary;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import de.os.hs.swa.quiz.control.EditQuestionService;
import de.os.hs.swa.quiz.entity.Question;

@Path("/quizzes/{quizID}/edit/{questionNr}")
@Tag(name = "Edit Qustion")
public class EditRessource {
    //TODO check for authentication
    @Inject
    EditQuestionService questionService;

    @GET
    @Operation(description = "gets the Question for the creator in format in wich it can be edited")
    public Question getQuestionByNumber(@PathParam("quizID") Long quizID, @PathParam("questionNr") int questionNr){
        return questionService.getEditableQuestion(quizID, questionNr);
    }

    @Transactional
    @PUT
    @Operation(description = "replace question of given Number with new question, for creator")
    public Question editQuestionByNumber(@PathParam("quizID") Long quizID, @PathParam("questionNr") int questionNr, Question question){
        return questionService.updateQuestion(quizID, questionNr, question);
    }

    @Transactional
    @DELETE
    @Operation(description = "delet question of number, only allowed for creator")
    public void removeQuestionByNumber(@PathParam("quizID") Long quizID, @PathParam("questionNr") int questionNr){
        questionService.deleteQuestion(quizID, questionNr);
    }
}
