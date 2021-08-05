package de.os.hs.swa.quiz.control;


import de.os.hs.swa.quiz.entity.Question;
import de.os.hs.swa.quiz.entity.Quiz;

//@author: Laura Peter
public interface QuizLogikService {
    public boolean checkValidQuiz(Quiz q);

    public boolean checkValidQuestion(Question q);
}
