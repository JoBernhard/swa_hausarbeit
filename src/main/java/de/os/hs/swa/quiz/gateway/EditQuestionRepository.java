package de.os.hs.swa.quiz.gateway;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;

import de.os.hs.swa.quiz.acl.UserAdapter;
import de.os.hs.swa.quiz.control.EditQuestionService;
import de.os.hs.swa.quiz.control.QuizLogikService;
import de.os.hs.swa.quiz.entity.Answer;
import de.os.hs.swa.quiz.entity.Question;
import de.os.hs.swa.quiz.entity.Quiz;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.security.ForbiddenException;

//@author: Johanna Bernhard, Laura Peter

@RequestScoped
public class EditQuestionRepository implements EditQuestionService, PanacheRepository<Question>{

    @Inject
    PanacheRepository<Quiz> quizRepository; 

    @Inject
    PanacheRepository<Answer> answerRepository;

    @Inject
    QuizLogikService logik;

    @Inject
    UserAdapter userService;

    @Override
    public Question updateQuestion(Long quizID, int questionNr, Question question) {
        if(checkValidQuestion(question)){
            Quiz q = quizRepository.findById(quizID);
            if(q!= null){
                Question questionToUpdate = find("quiz_id = ?1 and questionnr = ?2", quizID, questionNr).firstResult();
                if(questionToUpdate!=null){
                    if(userService.isAuthorizedToEdit(q.getCreatorName())){
                        if(checkValidQuestion(question)){
                            questionToUpdate.setText(question.getText());

                            for(Answer a: questionToUpdate.getAnswers()){
                                answerRepository.delete(a);
                            }
                            
                            for(Answer a: question.getAnswers()){
                                a.setQuestion(questionToUpdate);
                                answerRepository.persist(a);
                            }

                            questionToUpdate.getAnswers().clear();
                            questionToUpdate.getAnswers().addAll(question.getAnswers());
                        } 
                        return questionToUpdate;
                    }else{
                        throw new ForbiddenException();
                    }
                }else{
                    throw new NotFoundException();
                }
            }else{
                throw new NotFoundException("Quiz with id: "+ quizID+ " dosen't exist");
            }
        }else {
            throw new BadRequestException();
        }
    }

    @Override
    public void deleteQuestion(Long quizID, int questionNr) {
        Question todelete = getEditableQuestion(quizID, questionNr);
        if(todelete != null){
            delete(todelete);
        }else {
            throw new NotFoundException();
        }
        
    }

    @Override
    public Question getEditableQuestion(Long quizID, int questionNr) {
        Quiz quiz = quizRepository.findById(quizID);
        if(quiz!=null){
            if(userService.isAuthorizedToEdit(quiz.getCreatorName())){
                Question q = find("quiz_id = ?1 and questionnr = ?2", quizID, questionNr).firstResult();
                if(q != null){
                    return q;
                }else{
                    throw new NotFoundException("Qustion with Nr: "+ questionNr+ " dosen't exist in Quiz with id: "+quizID);
                }
            }else{
                throw new ForbiddenException();
            }
        }else{
            throw new NotFoundException("Quiz with ID: "+ quizID+ " dosen't exist");
        }
        
    }

    private boolean checkValidQuestion(Question q){
        return logik.checkValidQuestion(q);
    }
    
}
