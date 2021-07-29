package de.os.hs.swa.quiz.gateway;
import java.util.Collection;
import java.util.stream.Collectors;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;

import de.os.hs.swa.quiz.control.EditQuizService;
import de.os.hs.swa.quiz.control.QuizEditDTO;
import de.os.hs.swa.quiz.control.QuizListDTO;
import de.os.hs.swa.quiz.entity.Question;
import de.os.hs.swa.quiz.entity.Quiz;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

//@author: Johannna Benrhard

@RequestScoped
public class EditQuizRepository implements EditQuizService, PanacheRepository<Quiz> {
    //TODO: Account handeling
    @Inject
    PanacheRepository<Question> questionRepo;

    @Override
    public Collection<QuizListDTO> getOwnQuizzes(Long UserID) {
        //TODO: filter for creator
        Collection<QuizListDTO> dtos;
        dtos = streamAll().map(q -> quizToListDTO(q)).collect(Collectors.toList());
        return dtos;
    }

    @Override
    public Quiz getEditableQuiz(Long quizID) {
        // TODO error handeling
        // find Qustions

        return findById(quizID);
    }

    @Override
    public Question addQuestionToQuiz(Long quizID, Question question) {
        // TODO Auto-generated method stub
        if(checkValidQuestion(question)){
            question.setQuiz(findById(quizID));
            questionRepo.persist(question);
            return question;
        }else{
            throw new BadRequestException("Question dosen't fullfill Requirements");
        }
    }

    @Override
    public Quiz updateQuiz(Long quizID, Quiz updatedQuiz) {
        // TODO Auto-generated method stub
        if(checkValidQuiz(updatedQuiz)){
            updatedQuiz.setId(quizID);
            persist(updatedQuiz);
            return updatedQuiz;
        }else{
            throw new BadRequestException("Quiz dosen't fullfill Requirements");
        }
    }

    @Override
    public void deletQuizByID(Long quizID) {
        // TODO Auto-generated method stub
        Quiz toDelete = findById(quizID);
        if(toDelete!=null){
            delete(toDelete);
        }else{
            throw new NotFoundException();
        }
    }

    @Override
    public Quiz createNewQuiz(Quiz quiz) {
        if(checkValidQuiz(quiz)){
            persist(quiz);
            return quiz;
        } else{
            throw new BadRequestException("Quiz dosen't fullfill Requirements");
        }
    }

    private QuizListDTO quizToListDTO(Quiz q){
        QuizListDTO dto = new QuizListDTO();
        dto.title = q.getTitle();
        dto.linktToFirstQuestion = "";
        dto.linktToEdit = "quizzes/"+q.getId()+"/edit";
        dto.numberOfQuestions =0;
        return dto;
    }

    private boolean checkValidQuestion(Question q){
        return true;
    }

    private boolean checkValidQuiz(Quiz q){
        return true;
    }

    

    
}
