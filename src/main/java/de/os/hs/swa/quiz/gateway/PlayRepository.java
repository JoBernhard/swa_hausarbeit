package de.os.hs.swa.quiz.gateway;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import de.os.hs.swa.quiz.control.PlayService;
import de.os.hs.swa.quiz.control.DOTs.PlayQuestionDTO;
import de.os.hs.swa.quiz.control.DOTs.ResultDTO;
import de.os.hs.swa.quiz.entity.Answer;
import de.os.hs.swa.quiz.entity.Question;
import de.os.hs.swa.quiz.entity.Quiz;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;

//@author: Johanna Bernhard, Laura Peter

@RequestScoped
public class PlayRepository implements PlayService, PanacheRepository<Answer> {

    @ConfigProperty(name = "play.points")
    Integer points;

    @Inject
    PanacheRepository<Quiz> quizRepository;

    @Inject
    PanacheRepository<Question> questionRepository;

    @Override
    public PlayQuestionDTO chooseQuiz(Long quizID) {
        Question q = questionRepository.find("quiz_id", Sort.by("questionnr"), quizID).firstResult();
        if(q != null){
            return questionToPlayDTO(q);
        }else{
            throw new NotFoundException("Quiz with ID "+ quizID+ " dosen#t exist");
        }
    }

    @Override
    public PlayQuestionDTO getQuestion(Long quizID, int questionNr) {
        Question q = questionRepository.find("quiz_id = ?1 and questionNr = ?2", quizID, questionNr).firstResult();

        if(q==null){
            throw new NotFoundException();
        }
        return questionToPlayDTO(q);
    }

    @Override
    public ResultDTO answerQuestion(Long quizID, int questionNr, int answerNr) {

        Question question = questionRepository.find("quiz_id = ?1 and questionnr = ?2", quizID, questionNr).firstResult();
        
        Answer answer;
        if(question != null){
            answer = find("question_id = ?1 and answernr = ?2", question.getId(), answerNr).firstResult();
            if(answer==null){
                throw new NotFoundException("Answer dosen't exist");
            }
        }else{
            throw new NotFoundException("Question dosen't exist");
        }

        ResultDTO result = new ResultDTO();
        result.correctAnswers = stream("question_id = ?1 and iscorrect = true", question.getId())
                                    .map(a->a.getNumber()).collect(Collectors.toList());
       
        if(answer.getIsCorrect()){
            result.points = points;         
        }else{
            result.points = 0;
        }

        Question nextQuestion = getNextQuestion(quizID, questionNr);//questionRepository.find("quiz_id = ?1 and questionNr = ?2", quizID, questionNr+1).firstResult();
        //System.out.println("NEXT QUESTION: "+nextQuestion.getText());
        if(nextQuestion != null){
            result.linkToNextQuestion = "/quizzes/"+quizID+"/play/"+ nextQuestion.getQuestionNr();
        }else{
            result.linkToNextQuestion = "";
        }
        
        return result;
    }

    private Question getNextQuestion(Long quizID, int currentQuestion){
        Question next = questionRepository.find("quiz_id = ?1 and questionnr > ?2", Sort.by("questionnr"), quizID, currentQuestion).firstResult();
        return next;
    }
    

    private PlayQuestionDTO questionToPlayDTO(Question q){
        PlayQuestionDTO playQuestionDTO = new PlayQuestionDTO();
        playQuestionDTO.text = q.getText();

        Map<Integer,String> answers = new HashMap<>();
        for(Answer a : list("question_id", q.getId())){
            answers.put(a.getNumber(), a.getText());
        }
        playQuestionDTO.answers = answers;
        return playQuestionDTO;
    }
}
