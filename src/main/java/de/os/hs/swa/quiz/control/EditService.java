package de.os.hs.swa.quiz.control;

import de.os.hs.swa.quiz.entity.Question;

public interface EditService {
    public Question addQuestion(Long quizID, Question question);
    public Question updateQuestion(Long quizID, int questionNr, Question question);
    public void deleteQuiz(Long quizID);
    public Question getEditableQuestion(Long quizID, int questionNr);

}
