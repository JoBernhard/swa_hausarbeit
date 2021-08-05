package de.os.hs.swa.quiz.boundary;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import de.os.hs.swa.quiz.control.EditQuestionService;
import de.os.hs.swa.quiz.control.EditQuizService;
import de.os.hs.swa.quiz.control.DOTs.AnswerDTO;
import de.os.hs.swa.quiz.control.DOTs.QuestionDTO;
import de.os.hs.swa.quiz.entity.Answer;
import de.os.hs.swa.quiz.entity.Question;

//@author: Johanna Bernhard

@Path("/quizzes/{quizID}/edit/{questionNr}")
@Tag(name = "Edit Qustion")
public class EditRessource {
    @Inject
    EditQuestionService questionService;

    @Inject
    EditQuizService editQuizService;

    @GET
    @Operation(description = "gets the Question for the creator in format in which it can be edited")
    public QuestionDTO getQuestionByNumber(@Parameter(example = "200")@PathParam("quizID") Long quizID,@Parameter(example = "1") @PathParam("questionNr") int questionNr){
        return new QuestionDTO(questionService.getEditableQuestion(quizID, questionNr));
        
    }

    @Transactional
    @PUT
    @Operation(description = "replaces question of given Number with new question, only allowed for creator")
    public QuestionDTO editQuestionByNumber(@Parameter(example = "200") @PathParam("quizID") Long quizID,@Parameter(example = "1") @PathParam("questionNr") int questionNr, QuestionDTO question){
        return new QuestionDTO(questionService.updateQuestion(quizID, questionNr, dtoTQuestion(question)));
    }

    @Transactional
    @DELETE
    @Operation(description = "deletes question of given number, only allowed for creator")
    public void removeQuestionByNumber(@Parameter(example = "100")@PathParam("quizID") Long quizID,@Parameter(example = "1") @PathParam("questionNr") int questionNr){
        questionService.deleteQuestion(quizID, questionNr);
    }


    //@author: Laura Peter
    private Question dtoTQuestion(QuestionDTO dto){
        Question q = new Question();
        q.setText(dto.getText());
        
        ArrayList<Answer> answers = new ArrayList<>();
        int answerIndex = 1;
        for(AnswerDTO answerDTO : dto.getAnswers()){
            Answer answerToAdd = new Answer();
            answerToAdd.setText(answerDTO.getText());
            answerToAdd.setNumber(answerIndex++);
            answerToAdd.setIsCorrect(answerDTO.getIsCorrect());
            answerToAdd.setQuestion(q);
            answers.add(answerToAdd);
        }
        q.setAnswers(answers);
        return q; 
    }
}
