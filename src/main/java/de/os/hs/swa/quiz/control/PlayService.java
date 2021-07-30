package de.os.hs.swa.quiz.control;

public interface PlayService {
    public PlayQuestionDTO chooseQuiz(Long quizID);
    public ResultDTO answerQuestion(Long quizID, int questionNr, int answerNr);
    public PlayQuestionDTO getQuestion(Long quizID, int questionNr);
}
