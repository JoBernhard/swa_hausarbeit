package de.os.hs.swa.quiz.gateway;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;

import de.os.hs.swa.quiz.control.EditQuestionService;
import de.os.hs.swa.quiz.control.QuizLogikService;
import de.os.hs.swa.quiz.entity.Answer;
import de.os.hs.swa.quiz.entity.Question;
import de.os.hs.swa.quiz.entity.Quiz;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

//@author: Johanna Bernhard

@RequestScoped
public class EditQuestionRepository implements EditQuestionService, PanacheRepository<Question>{

    //TODO check if user is creator
    @Inject
    PanacheRepository<Quiz> quizRepository; 

    @Inject
    PanacheRepository<Answer> answerRepository;

    @Inject
    QuizLogikService logik;

    @Override
    public Question updateQuestion(Long quizID, int questionNr, Question question) {
        // TODO Error handeling
        if(checkValidQuestion(question)){
            Quiz q = quizRepository.findById(quizID);
            if(q!= null){
                question.setQuiz(q);
                question.setQuestionNr(questionNr);
                persist(question);
                return question;
            }else{
                throw new NotFoundException("Quiz with id: "+ quizID+ " dosen't exist");
            }
        }else {
            throw new BadRequestException();
        }
    }

    @Override
    public void deleteQuestion(Long quizID, int questionNr) {
        Question todelete = getEditableQuestion(quizID, questionNr);
        if(todelete != null){
            delete(todelete);
        }else {
            throw new NotFoundException();
        }
        
    }

    @Override
    public Question getEditableQuestion(Long quizID, int questionNr) {
        Question q = find("quiz_id = ?1 and questionnr = ?2", quizID, questionNr).firstResult();
        if(q != null){
            q.setAnswers(answerRepository.list("question_id", q.getId()));
            return q;
        }else{
            throw new NotFoundException();
        }
    }

    private boolean checkValidQuestion(Question q){
        return logik.checkValidQuestion(q);
    }
    
}
