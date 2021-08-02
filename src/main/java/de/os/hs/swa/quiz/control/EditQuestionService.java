package de.os.hs.swa.quiz.control;

import de.os.hs.swa.quiz.control.DOTs.QuestionDTO;
import de.os.hs.swa.quiz.entity.Question;

public interface EditQuestionService {
    public Question updateQuestion(Long quizID, int questionNr, Question question);
    public void deleteQuestion(Long quizID, int questionNr);
    public Question getEditableQuestion(Long quizID, int questionNr);

}
