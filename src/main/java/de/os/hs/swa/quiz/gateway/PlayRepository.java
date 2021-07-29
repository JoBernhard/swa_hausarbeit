package de.os.hs.swa.quiz.gateway;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import de.os.hs.swa.quiz.control.PlayQuestionDTO;
import de.os.hs.swa.quiz.control.PlayService;
import de.os.hs.swa.quiz.control.ResultDTO;
import de.os.hs.swa.quiz.entity.Question;
import de.os.hs.swa.quiz.entity.Quiz;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

//@author: Johanna Bernhard

@RequestScoped
public class PlayRepository implements PlayService {

    @Inject
    PanacheRepository<Quiz> quizRepository;

    @Inject
    PanacheRepository<Question> questionRepository;

    @Override
    public PlayQuestionDTO chooseQuiz(Long quizID) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public PlayQuestionDTO getQuestion(Long quizID, int questionNr) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResultDTO answerQuestion(Long quizID, int questionNr, int answerNr) {
        // TODO Auto-generated method stub
        return null;
    }
    

    private PlayQuestionDTO questionToPlayDTO(Question q){
        return null;
    }
}
