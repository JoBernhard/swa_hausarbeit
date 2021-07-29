package de.os.hs.swa.quiz.gateway;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;

import de.os.hs.swa.quiz.control.EditQuestionService;
import de.os.hs.swa.quiz.entity.Question;
import de.os.hs.swa.quiz.entity.Quiz;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

//@author: Johanna Bernhard

@RequestScoped
public class EditQuestionRepository implements EditQuestionService, PanacheRepository<Question>{

    //check if user is creator
    @Inject
    PanacheRepository<Quiz> quizRepository; 

    @Override
    public Question updateQuestion(Long quizID, int questionNr, Question question) {
        // TODO Error handeling
        if(checkValidQuestion(question)){
            question.setQuiz(quizRepository.findById(quizID));
            question.setQuestionNr(questionNr);
            persist(question);
            return question;
        }else {
            throw new BadRequestException();
        }
    }

    @Override
    public void deleteQuestion(Long quizID, int questionNr) {
        // TODO 
        Question todelete = getEditableQuestion(quizID, questionNr);
        if(todelete != null){
            delete(todelete);
        }else {
            throw new NotFoundException();
        }
        
    }

    @Override
    public Question getEditableQuestion(Long quizID, int questionNr) {
        // TODO Error handeling
        return find("quiz_id, number", quizID, questionNr).firstResult();
        //return null;
    }

    private boolean checkValidQuestion(Question q){
        //TODO check if conditions are met
        return true;
    }
    
}
