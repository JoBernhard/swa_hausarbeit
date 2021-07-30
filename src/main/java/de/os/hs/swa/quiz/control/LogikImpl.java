package de.os.hs.swa.quiz.control;

import javax.enterprise.context.RequestScoped;

import de.os.hs.swa.quiz.entity.Question;
import de.os.hs.swa.quiz.entity.Quiz;

@RequestScoped
public class LogikImpl implements QuizLogikService{

    @Override
    public boolean checkValidQuiz(Quiz q) {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean checkValidQuestion(Question q) {
        // TODO Auto-generated method stub
        return true;
    }
    
}
