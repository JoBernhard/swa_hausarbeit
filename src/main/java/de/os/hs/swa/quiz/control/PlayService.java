package de.os.hs.swa.quiz.control;

public interface PlayService {
    public QuestionDTO chooseQuiz(Long QuizID);
    public ResultDTO answerQuestion(Long QuizID);
    public QuestionDTO getQuestion(Long QuizID, Long QuestionID);
}
