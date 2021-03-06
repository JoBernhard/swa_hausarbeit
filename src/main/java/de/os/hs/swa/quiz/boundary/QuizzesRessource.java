package de.os.hs.swa.quiz.boundary;

import java.util.ArrayList;
import java.util.Collection;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import de.os.hs.swa.quiz.acl.UserAdapter;
import de.os.hs.swa.quiz.control.EditQuestionService;
import de.os.hs.swa.quiz.control.EditQuizService;
import de.os.hs.swa.quiz.control.DOTs.AnswerDTO;
import de.os.hs.swa.quiz.control.DOTs.QuestionDTO;
import de.os.hs.swa.quiz.control.DOTs.QuizEditDTO;
import de.os.hs.swa.quiz.control.DOTs.QuizListDTO;
import de.os.hs.swa.quiz.entity.Answer;
import de.os.hs.swa.quiz.entity.Question;
import de.os.hs.swa.quiz.entity.Quiz;

//@autor: Johanna Bernhard, Laura Peter

@Path("/quizzes")
@Tag(name = "Own Quizzes")
public class QuizzesRessource {
    @Inject
    EditQuizService editQuizService;
    @Inject
    EditQuestionService editQuestionService;
    @Inject
    UserAdapter userService;

    @GET
    @Operation(description = "get the list of own created quizzes")
    public Collection<QuizListDTO> getOwnQuizzes(@QueryParam("page") int page){
        return editQuizService.getOwnQuizzes(userService.getCurrentUser(), page);
    }

    @Transactional
    @POST
    @Operation(description = "create a new Quiz in a category")
    public Response createNewQuiz(QuizEditDTO quiz){
        if(quiz!=null){
            Quiz q = editQuizService.createNewQuiz(dtoToQuiz(quiz));
            return Response.status(Response.Status.CREATED).entity(new QuizEditDTO(q)).build();
        } else {
            throw new BadRequestException("Quiz shall not be null");
        }
    }

    @Path("{quizID}/edit")
    @GET
    @Operation(description = "get created quiz by id to edit")
    public QuizEditDTO getQuizByID(@Parameter(example = "100") @PathParam("quizID") Long quizID){
        return new QuizEditDTO(editQuizService.getEditableQuiz(quizID));       
    }

    @Transactional
    @Path("{quizID}/edit")
    @POST
    @Operation(description = "add new Question to quiz allowed for creator")
    public QuestionDTO addQuestionToQuiz(@Parameter(example = "100") @PathParam("quizID") Long quizID, QuestionDTO question){
        return new QuestionDTO(editQuizService.addQuestionToQuiz(quizID, dtoToQuestion(question)));
    }

    @Transactional
    @Path("{quizID}/edit")
    @PUT
    @Operation(description = "override quiz with given id with new quiz only for creator, returns the edited quiz")
    public QuizEditDTO editQuiz(@Parameter(example = "100") @PathParam("quizID") Long quizID, QuizEditDTO quiz){
        return new QuizEditDTO(editQuizService.updateQuiz(quizID, dtoToQuiz(quiz)));
    }

    @Transactional
    @Path("{quizID}/edit")
    @DELETE
    @Operation(description = "delete quiz with id only for creator")
    public void deletQuizByID(@Parameter(example = "100") @PathParam("quizID") Long quizID){
        editQuizService.deletQuizByID(quizID);       
    }

    private Quiz dtoToQuiz(QuizEditDTO dto){
        Quiz q = new Quiz();
        q.setTitle(dto.getTitle());
        q.setCreatorName(userService.getCurrentUser());
        q.setCategory(dto.getCategoryName());
        q.setQuestions(dtosToQuestions(dto.getQuestions()));
        return q;
    }

    private Collection<Question> dtosToQuestions(Collection<QuestionDTO> dtos){
        if(dtos!=null && !dtos.isEmpty()){
            ArrayList<Question> questions = new ArrayList<>();
            int questionIndex = 1;
            for(QuestionDTO questionDTO : dtos){
                Question questionToAdd = new Question();
                questionToAdd.setText(questionDTO.getText());
                questionToAdd.setQuestionNr(questionIndex++);
                questionToAdd.setAnswers(dtosToAnswers(questionDTO.getAnswers(),questionToAdd));
                questions.add(questionToAdd);
            }
            return questions;
        }else{
            throw new BadRequestException("Questions shall not be null");
        }
    }

    private Question dtoToQuestion(QuestionDTO dto){
        Question q = new Question();
        q.setText(dto.getText());
        q.setAnswers(dtosToAnswers(dto.getAnswers(), q));
        return q;
    }

    private Collection<Answer> dtosToAnswers(Collection<AnswerDTO> dtos, Question question){
        ArrayList<Answer> answers = new ArrayList<>();
        int answerIndex = 1;
        for(AnswerDTO answerDTO : dtos){
            Answer answerToAdd = new Answer();
            answerToAdd.setText(answerDTO.getText());
            answerToAdd.setNumber(answerIndex++);
            answerToAdd.setIsCorrect(answerDTO.getIsCorrect());
            answerToAdd.setQuestion(question);
            answers.add(answerToAdd);
        }
        return answers;
    }

}
