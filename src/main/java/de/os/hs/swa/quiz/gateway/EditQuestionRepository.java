package de.os.hs.swa.quiz.gateway;

import javax.enterprise.context.RequestScoped;

import de.os.hs.swa.quiz.control.EditQuestionService;
import de.os.hs.swa.quiz.entity.Question;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@RequestScoped
public class EditQuestionRepository implements EditQuestionService, PanacheRepository<Question>{

    @Override
    public Question updateQuestion(Long quizID, int questionNr, Question question) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteQuiz(Long quizID) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Question getEditableQuestion(Long quizID, int questionNr) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
