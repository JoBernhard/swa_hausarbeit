package de.os.hs.swa.quiz.gateway;
import java.util.Collection;

import de.os.hs.swa.quiz.control.EditQuizService;
import de.os.hs.swa.quiz.control.QuizListDTO;
import de.os.hs.swa.quiz.entity.Question;
import de.os.hs.swa.quiz.entity.Quiz;

public class EditQuizRepository implements EditQuizService{

    @Override
    public Collection<QuizListDTO> getOwnQuizzes(Long UserID) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Quiz getEditableQuiz(Long quizID) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Question addQuestionToQuiz(Long quizID, Question question) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Quiz updateQuiz(Long quizID, Quiz updatedQuiz) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deletQuizByID(Long quizID) {
        // TODO Auto-generated method stub
        
    }

    
}
