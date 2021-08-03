package de.os.hs.swa.quiz.gateway;
import java.util.Collection;
import java.util.stream.Collectors;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import de.os.hs.swa.quiz.acl.UserAdapter;
import de.os.hs.swa.quiz.control.EditQuizService;
import de.os.hs.swa.quiz.control.QuizLogikService;
import de.os.hs.swa.quiz.control.DOTs.QuizListDTO;
import de.os.hs.swa.quiz.entity.Answer;
import de.os.hs.swa.quiz.entity.Question;
import de.os.hs.swa.quiz.entity.Quiz;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Page;
import io.quarkus.security.UnauthorizedException;

//@author: Johannna Benrhard, Laura Peter

@RequestScoped
public class EditQuizRepository implements EditQuizService, PanacheRepository<Quiz> {
    @Inject
    PanacheRepository<Question> questionRepo;

    @Inject
    PanacheRepository<Answer> answerRepository;

    @Inject
    QuizLogikService logik;

    @Inject
    UserAdapter userService;

    @ConfigProperty(name = "page.size")
    Integer pageSize;

    @Override
    @Transactional
    public Collection<QuizListDTO> getOwnQuizzes(String UserName, int page) {
        Collection<QuizListDTO> dtos;
        PanacheQuery<Quiz> ownQuizzes = find("creatorname", UserName);
        dtos = ownQuizzes.page(Page.of(page, pageSize)).stream().map(q -> quizToListDTO(q)).collect(Collectors.toList());
        return dtos;
    }

    @Override
    public Quiz getEditableQuiz(Long quizID) {
        //TODO check if correct
        Quiz q = findById(quizID);
        if(q != null){
            System.out.println(q.getCreatorName());
            if(userService.isAuthorizedToEdit(q.getCreatorName())){
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
                    question.setQuestionNr(q.getQuestions().size()+1);
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
        //TODO remove unused code
        Quiz toUpdate = findById(quizID);
        if(toUpdate != null){
            if(userService.isAuthorizedToEdit(toUpdate.getCreatorName())){
                if(checkValidQuiz(updatedQuiz)){
                    toUpdate.setCategory(updatedQuiz.getCategory());
                    toUpdate.setTitle(updatedQuiz.getTitle());

                    for(Question question : toUpdate.getQuestions()){
                        questionRepo.delete(question);
                    }

                    for(Question question : updatedQuiz.getQuestions()){
                        question.setQuiz(toUpdate);
                        questionRepo.persist(question);
                    }

                    toUpdate.getQuestions().clear();
                    toUpdate.getQuestions().addAll(updatedQuiz.getQuestions());

                    return toUpdate;
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
            setForeignKeysOfQuestionAndAnswer(quiz);
            persist(quiz);
            
            return quiz;
        } else{
            throw new BadRequestException("Quiz dosen't fullfill Requirements");
        }
    }

    private void setForeignKeysOfQuestionAndAnswer(Quiz quiz){
        for(Question question : quiz.getQuestions()){
            question.setQuiz(quiz);
            for(Answer answer : question.getAnswers()){
                answer.setQuestion(question);
            }
        }
    }

    private QuizListDTO quizToListDTO(Quiz q){
        QuizListDTO dto = new QuizListDTO();
        dto.title = q.getTitle();
        dto.linktToPlay = "quizzes/"+q.getId()+"/play";
        dto.linktToEdit = "quizzes/"+q.getId()+"/edit";
        return dto;
    }

    private boolean checkValidQuestion(Question q){
        return logik.checkValidQuestion(q);
    }

    private boolean checkValidQuiz(Quiz q){
        return logik.checkValidQuiz(q);
    }
    
}
