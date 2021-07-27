package de.os.hs.swa.quiz.control;

public interface PlayService {
    public PlayQuestionDTO chooseQuiz(Long QuizID);
    public ResultDTO answerQuestion(Long QuizID);
    public PlayQuestionDTO getQuestion(Long QuizID, Long QuestionID);
}
