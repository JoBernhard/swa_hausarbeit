package de.os.hs.swa.quiz.control;

import java.util.Collection;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.BadRequestException;

import de.os.hs.swa.quiz.acl.CategoryAdapter;
import de.os.hs.swa.quiz.entity.Answer;
import de.os.hs.swa.quiz.entity.Question;
import de.os.hs.swa.quiz.entity.Quiz;

//@author: Johanna Bernhard

@RequestScoped
public class LogikImpl implements QuizLogikService{
    @Inject
    CategoryAdapter categoryAdapter;

    @Override
    public boolean checkValidQuiz(Quiz q) {
        // TODO test if working
        if(q != null){
            
            if(q.getTitle().isEmpty()){
                throw new BadRequestException("Quiz Title can't be empty");
            }
            if(categoryAdapter.checkForCategory(q.getCategory().getName())== null){
                throw new BadRequestException("Category not found");
            }
            
            Collection<Question> questions = q.getQuestions();
            if(questions != null && !questions.isEmpty()){
                for(Question quest : questions){
                    if(!checkValidQuestion(quest)){
                        throw new BadRequestException("Question Nr"+quest.getQuestionNr()+" dosen't fullfill requrements");
                    }
                }
            }else{
                throw new BadRequestException("Quiz must contain questions");
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean checkValidQuestion(Question q) {
        //TODO test if working
        if(q != null){
            if(q.getText().isEmpty()){
                throw new BadRequestException("Question Text can't be empty");
            }
            Collection<Answer> answers = q.getAnswers();
            if(answers != null && !answers.isEmpty()){
                //check that at least one correct and one incorrect answer is provided for Question
                boolean correctProvided = false, wrongProvided=false;
                for(Answer answer : answers){
                    if(answer.getIsCorrect()){
                        correctProvided = true;
                    }else{
                        wrongProvided = true;
                    }
                }
                return correctProvided && wrongProvided;
            }else{
                throw new BadRequestException("Question needs Answers");
            }
        }
        return false;
    }
    
}
