package de.os.hs.swa.quiz.gateway;
import java.util.Collection;
import java.util.stream.Collectors;

import javax.enterprise.context.RequestScoped;

import de.os.hs.swa.quiz.control.EditQuizService;
import de.os.hs.swa.quiz.control.QuizListDTO;
import de.os.hs.swa.quiz.entity.Question;
import de.os.hs.swa.quiz.entity.Quiz;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

//@author: Johannna Benrhard

@RequestScoped
public class EditQuizRepository implements EditQuizService, PanacheRepository<Quiz> {

    @Override
    public Collection<QuizListDTO> getOwnQuizzes(Long UserID) {
        //TODO: filter for creator
        Collection<QuizListDTO> dtos;
        dtos = streamAll().map(q -> quizToListDTO(q)).collect(Collectors.toList());
        return dtos;
    }

    @Override
    public Quiz getEditableQuiz(Long quizID) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Question addQuestionToQuiz(Long quizID, Question question) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Quiz updateQuiz(Long quizID, Quiz updatedQuiz) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deletQuizByID(Long quizID) {
        // TODO Auto-generated method stub
        
    }

    private QuizListDTO quizToListDTO(Quiz q){
        QuizListDTO dto = new QuizListDTO();
        dto.title = q.getTitle();
        dto.linktToFirstQuestion = "";
        dto.numberOfQuestions =0;
        return dto;
    }
}
