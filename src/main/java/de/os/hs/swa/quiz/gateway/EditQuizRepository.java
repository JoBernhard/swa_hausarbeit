package de.os.hs.swa.quiz.gateway;
import java.util.Collection;
import java.util.stream.Collectors;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;

import de.os.hs.swa.quiz.acl.UserAdapter;
import de.os.hs.swa.quiz.control.EditQuizService;
import de.os.hs.swa.quiz.control.QuizLogikService;
import de.os.hs.swa.quiz.control.DOTs.QuizListDTO;
import de.os.hs.swa.quiz.entity.Question;
import de.os.hs.swa.quiz.entity.Quiz;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.security.UnauthorizedException;

//@author: Johannna Benrhard

@RequestScoped
public class EditQuizRepository implements EditQuizService, PanacheRepository<Quiz> {
    @Inject
    PanacheRepository<Question> questionRepo;

    @Inject
    QuizLogikService logik;

    @Inject
    UserAdapter userService;

    @Override
    public Collection<QuizListDTO> getOwnQuizzes(String UserName) {
        Collection<QuizListDTO> dtos;
        dtos = stream("creatorname", UserName).map(q -> quizToListDTO(q)).collect(Collectors.toList());
        return dtos;
    }

    @Override
    public Quiz getEditableQuiz(Long quizID) {
        Quiz q = findById(quizID);
        if(q != null){
            if(userService.isAuthorizedToEdit(q.getCreatorName())){
                q.setQuestions(questionRepo.list("quiz_id", q.getId()));
                return q;
            }else{
                throw new UnauthorizedException();
            }
        }else{
            throw new NotFoundException();
        }
    }

    @Override
    public Question addQuestionToQuiz(Long quizID, Question question) {
        Quiz q = findById(quizID);
        if(q != null){
            if(userService.isAuthorizedToEdit(q.getCreatorName())){
                if(checkValidQuestion(question)){
                    question.setQuiz(q);
                    questionRepo.persist(question);
                    return question;
                }else{
                    throw new BadRequestException("Question dosen't fullfill Requirements");
                }
            }else{
                throw new UnauthorizedException();
            }
        }else{
            throw new NotFoundException();
        }
    }

    @Override
    public Quiz updateQuiz(Long quizID, Quiz updatedQuiz) {
        Quiz toUpdate = findById(quizID);
        if(toUpdate != null){
            if(userService.isAuthorizedToEdit(toUpdate.getCreatorName())){
                if(checkValidQuiz(updatedQuiz)){
                    updatedQuiz.setId(quizID);
                    persist(updatedQuiz);
                    return updatedQuiz;
                }else{
                    throw new BadRequestException("Quiz dosen't fullfill Requirements");
                } 
            }else{
                throw new UnauthorizedException();
            }
        }else{
            throw new NotFoundException();
        }
        
    }

    @Override
    public void deletQuizByID(Long quizID) {
        Quiz toDelete = findById(quizID);
        if(toDelete!=null){
            if(userService.isAuthorizedToEdit(toDelete.getCreatorName())){
                delete(toDelete);
            }else{
                throw new UnauthorizedException();
            }
        }else{
            throw new NotFoundException();
        }
    }

    @Override
    public Quiz createNewQuiz(Quiz quiz) {
        if(checkValidQuiz(quiz)){
            quiz.setCreatorName(userService.getCurrentUser());
            persist(quiz);
            return quiz;
        } else{
            throw new BadRequestException("Quiz dosen't fullfill Requirements");
        }
    }

    private QuizListDTO quizToListDTO(Quiz q){
        QuizListDTO dto = new QuizListDTO();
        dto.title = q.getTitle();
        dto.linktToFirstQuestion = "quizzes/"+q.getId()+"/play";
        dto.linktToEdit = "quizzes/"+q.getId()+"/edit";
        dto.numberOfQuestions =0;
        return dto;
    }

    private boolean checkValidQuestion(Question q){
        return logik.checkValidQuestion(q);
    }

    private boolean checkValidQuiz(Quiz q){
        return logik.checkValidQuiz(q);
    }

    

    
}
