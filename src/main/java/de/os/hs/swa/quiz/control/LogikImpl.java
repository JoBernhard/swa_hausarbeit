package de.os.hs.swa.quiz.control;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validator;
import javax.ws.rs.BadRequestException;

import de.os.hs.swa.quiz.acl.CategoryAdapter;
import de.os.hs.swa.quiz.entity.Answer;
import de.os.hs.swa.quiz.entity.Question;
import de.os.hs.swa.quiz.entity.Quiz;

//@author: Johanna Bernhard, Laura Peter

@RequestScoped
public class LogikImpl implements QuizLogikService{
    @Inject
    CategoryAdapter categoryAdapter;

    @Inject
    Validator validator;

    @Override
    public boolean checkValidQuiz(Quiz q) {
        Set<ConstraintViolation<Quiz>> violations = validator.validate(q);
        if(violations.isEmpty() && q != null){
            if(categoryAdapter.checkForCategory(q.getCategory().getName())== null){
                throw new BadRequestException("Category not found");
            }
            
            Collection<Question> questions = q.getQuestions();
                for(Question question : questions){
                    checkValidQuestion(question);
                }
            return true;
            
        }
        String violationMessage = violations.stream()
        .map(cv -> cv.getMessage())
        .collect(Collectors.joining(", "));
        throw new BadRequestException(violationMessage);
        //return false;
    }

    @Override
    public boolean checkValidQuestion(Question q) {
        Set<ConstraintViolation<Question>> questionViolations = validator.validate(q);
        if(questionViolations.isEmpty()){
            
            Collection<Answer> answers = q.getAnswers();
            if(correctAnswerExists(answers)){
                for(Answer answer : answers){
                      checkValidAnswer(answer);
                }
            }
            return true;          
        }else{
            String violationMessage = questionViolations.stream()
            .map(cv -> cv.getMessage())
            .collect(Collectors.joining(", "));
            throw new BadRequestException(violationMessage);
        }
        //return false;
    }

    private boolean checkValidAnswer(Answer answer){
        Set<ConstraintViolation<Answer>> answerViolations = validator.validate(answer);
        if(!answerViolations.isEmpty()){
            String violationMessage = answerViolations.stream()
            .map(cv -> cv.getMessage())
            .collect(Collectors.joining(", "));
            throw new BadRequestException(violationMessage);
        }
        return true;
    }

    //check that at least one correct and one incorrect answer is provided for Question and answertext not empty
    private boolean correctAnswerExists(Collection<Answer> answers){
        boolean correctProvided = false, wrongProvided=false;
        for(Answer answer : answers){
            if(answer.getIsCorrect()){
                correctProvided = true;
            }else{
                wrongProvided = true;
            }
        }
        if (!(correctProvided && wrongProvided)){
            throw new BadRequestException("Question must contain an Answer marked as correct");
        }
        return true;
    }
    
}
