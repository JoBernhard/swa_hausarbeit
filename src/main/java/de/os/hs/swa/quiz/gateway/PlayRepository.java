package de.os.hs.swa.quiz.gateway;

import javax.enterprise.context.RequestScoped;

import de.os.hs.swa.quiz.control.PlayQuestionDTO;
import de.os.hs.swa.quiz.control.PlayService;
import de.os.hs.swa.quiz.control.ResultDTO;


@RequestScoped
public class PlayRepository implements PlayService {

    @Override
    public PlayQuestionDTO chooseQuiz(Long QuizID) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResultDTO answerQuestion(Long QuizID) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public PlayQuestionDTO getQuestion(Long QuizID, Long QuestionID) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
