package de.os.hs.swa.quiz.control;

import javax.validation.Valid;

import de.os.hs.swa.quiz.entity.Question;
import de.os.hs.swa.quiz.entity.Quiz;

public interface QuizLogikService {
    public boolean checkValidQuiz(Quiz q);

    public boolean checkValidQuestion(Question q);
}
