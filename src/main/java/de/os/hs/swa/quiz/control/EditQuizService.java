package de.os.hs.swa.quiz.control;

import java.util.Collection;

import de.os.hs.swa.quiz.control.DOTs.QuizListDTO;
import de.os.hs.swa.quiz.entity.Question;
import de.os.hs.swa.quiz.entity.Quiz;

//@author: Laura Peter
public interface EditQuizService {
    public Collection<QuizListDTO> getOwnQuizzes(String UserName, int page);
    public Quiz getEditableQuiz(Long quizID);
    public Question addQuestionToQuiz(Long quizID, Question question);
    public Quiz updateQuiz(Long quizID, Quiz updatedQuiz);
    public void deletQuizByID(Long quizID);
    public Quiz createNewQuiz(Quiz quiz);
}
