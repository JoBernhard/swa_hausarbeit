package de.os.hs.swa.quiz.control;

import javax.enterprise.context.RequestScoped;

import de.os.hs.swa.quiz.entity.Question;
import de.os.hs.swa.quiz.entity.Quiz;

@RequestScoped
public class QuizLogikService {
    public boolean checkValidQuiz(Quiz q){
        return true;
    }

    public boolean checkValidQuestion(Question q){
        return true;
    }
}
