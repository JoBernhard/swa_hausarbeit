package de.os.hs.swa.quiz.gateway;

import de.os.hs.swa.quiz.control.EditQuestionService;
import de.os.hs.swa.quiz.entity.Question;

public class EditQuestionRepository implements EditQuestionService{

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
