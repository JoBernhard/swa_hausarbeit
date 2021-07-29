package de.os.hs.swa.quiz.gateway;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import de.os.hs.swa.quiz.control.PlayQuestionDTO;
import de.os.hs.swa.quiz.control.PlayService;
import de.os.hs.swa.quiz.control.ResultDTO;
import de.os.hs.swa.quiz.entity.Answer;
import de.os.hs.swa.quiz.entity.Question;
import de.os.hs.swa.quiz.entity.Quiz;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

//@author: Johanna Bernhard, Laura Peter

@RequestScoped
public class PlayRepository implements PlayService {

    @ConfigProperty(name = "play.points")
    Integer points;

    @Inject
    PanacheRepository<Quiz> quizRepository;

    @Inject
    PanacheRepository<Question> questionRepository;

    @Inject
    PanacheRepository<Answer> answerRepository;

    @Override
    public PlayQuestionDTO chooseQuiz(Long quizID) {
        // TODO Auto-generated method stub
        Question q = questionRepository.find("quiz_id, qustionNr", quizID, 1).singleResult();
        return null;
    }

    @Override
    public PlayQuestionDTO getQuestion(Long quizID, int questionNr) {
        Question q = questionRepository.find("quiz_id, qustionNr", quizID, questionNr).firstResult();

        if(q==null){
            throw new NotFoundException();
        }
        return questionToPlayDTO(q);
    }

    @Override
    public ResultDTO answerQuestion(Long quizID, int questionNr, int answerNr) {
        // TODO error handeling
        Answer a = answerRepository.find("quiz_id, qustionNr, answerNr", quizID, questionNr, answerNr).firstResult();

        if(a==null){
            throw new NotFoundException();
        }

        ResultDTO result = new ResultDTO();
        result.correctAnswer = a.getNumber();
       
        if(a.getIsCorrect()){
            result.points = points;         
        }

        Question nextQuestion = questionRepository.find("quiz_id, qustionNr", quizID, questionNr+1).firstResult();
        //TODO: find next question
        if(nextQuestion != null){
            result.linkToNextQuestion = "/quizzes/{quizID}/play/"+ (questionNr+1);
        }else{
            result.linkToNextQuestion = "";
        }
        
        return result;
    }
    

    private PlayQuestionDTO questionToPlayDTO(Question q){
        //TODO implement
        PlayQuestionDTO playQuestionDTO = new PlayQuestionDTO();
        playQuestionDTO.text = q.getText();

        Map<Integer,String> answers = new HashMap<>();
        for(Answer a : q.getAnswers()){
            answers.put(a.getNumber(), a.getText());
        }
        playQuestionDTO.answers = answers;
        return playQuestionDTO;
    }
}
