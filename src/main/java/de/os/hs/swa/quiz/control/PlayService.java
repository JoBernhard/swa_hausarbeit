package de.os.hs.swa.quiz.control;

import de.os.hs.swa.quiz.control.DOTs.PlayQuestionDTO;
import de.os.hs.swa.quiz.control.DOTs.ResultDTO;

public interface PlayService {
    public PlayQuestionDTO chooseQuiz(Long quizID);
    public ResultDTO answerQuestion(Long quizID, int questionNr, int answerNr);
    public PlayQuestionDTO getQuestion(Long quizID, int questionNr);
}
