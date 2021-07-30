package de.os.hs.swa.quiz.boundary;

import java.util.Collection;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import de.os.hs.swa.quiz.acl.UserAdapter;
import de.os.hs.swa.quiz.control.EditQuestionService;
import de.os.hs.swa.quiz.control.EditQuizService;
import de.os.hs.swa.quiz.control.DOTs.QuizEditDTO;
import de.os.hs.swa.quiz.control.DOTs.QuizListDTO;
import de.os.hs.swa.quiz.entity.Question;
import de.os.hs.swa.quiz.entity.Quiz;

//@autor: Johanna Bernhard

@Path("/quizzes")
@Tag(name = "Own Quizzes")
public class QuizzesRessource {
    @Inject
    EditQuizService editQuizService;
    @Inject
    EditQuestionService editQuistionService;

    @Inject
    UserAdapter userService;

//TODO: add security context
    @GET
    @Operation(description = "get the list of own created quizzes")
    public Collection<QuizListDTO> getOwnQuizzes(){
        return editQuizService.getOwnQuizzes(userService.getCurrentUser());
    }

    @Transactional
    @POST
    @Operation(description = "create a new Quiz in a category")
    public Quiz createNewQuiz(QuizEditDTO quiz){
        return editQuizService.createNewQuiz(dtoToQuiz(quiz));
    }

    @Path("{quizID}/edit")
    @GET
    @Operation(description = "get created quiz by id to edit")
    public Quiz getQuizByID(@PathParam("quizID") Long quizID){
        Quiz q = editQuizService.getEditableQuiz(quizID);
        if(userService.isAuthorizedToEdit("quizUsername")){
            return q;
        }
        return null;
    }

    @Transactional
    @Path("{quizID}/edit")
    @POST
    @Operation(description = "add new Question to quiz allowed for creator")
    public Question addQuestionToQuiz(@PathParam("quizID") Long quizID, Question question){
        Quiz q = editQuizService.getEditableQuiz(quizID);
        if(userService.isAuthorizedToEdit(q.getCreatorName())){
            return editQuizService.addQuestionToQuiz(quizID, question);
        }
        return null;
    }

    @Transactional
    @Path("{quizID}/edit")
    @PUT
    @Operation(description = "override quiz with given id with new quiz only for creator, returns the edited quiz")
    public Quiz editQuiz(@PathParam("quizID") Long quizID, QuizEditDTO quiz){
        Quiz q = editQuizService.getEditableQuiz(quizID);
        if(userService.isAuthorizedToEdit(q.getCreatorName())){
            return editQuizService.updateQuiz(quizID, dtoToQuiz(quiz));
        }
        return null;
    }

    @Transactional
    @Path("{quizID}/edit")
    @DELETE
    @Operation(description = "delete quiz with id only for creator")
    public void deletQuizByID(@PathParam("quizID") Long quizID){
        Quiz q = editQuizService.getEditableQuiz(quizID);
        if(userService.isAuthorizedToEdit(q.getCreatorName())){
            editQuizService.deletQuizByID(quizID);
        }
        
    }


    private Quiz dtoToQuiz(QuizEditDTO dto){
        Quiz q = new Quiz();
        q.setTitle(dto.getTitle());
        //TODO: q.setQuestions(dto.getQuestions());
        return q;
    }
}
