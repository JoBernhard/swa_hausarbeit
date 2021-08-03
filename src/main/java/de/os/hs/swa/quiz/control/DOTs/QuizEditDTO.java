package de.os.hs.swa.quiz.control.DOTs;
import java.util.Collection;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import de.os.hs.swa.category.entity.Category;
import de.os.hs.swa.quiz.entity.Quiz;

//@author: Laura Peter

public class QuizEditDTO {
    @NotBlank
    private Category categoryName;
    @NotBlank
    private String title;
    @NotEmpty
    private Collection<QuestionDTO> questions;
    private String creatorName;

    public QuizEditDTO(String title, Collection<QuestionDTO> questions){
        this.title = title;
        this.questions = questions;
    }


    public QuizEditDTO() {
    }

    public QuizEditDTO(Category category, String title, Collection<QuestionDTO> questions) {
       
        this.categoryName = category;
        this.title = title;
        this.questions = questions;
    }

    public QuizEditDTO(Quiz quiz){
        this.categoryName = quiz.getCategory();
        this.title = quiz.getTitle();
        this.creatorName = quiz.getCreatorName();
        this.questions =  quiz.getQuestions().stream().map(a->new QuestionDTO(a)).collect(Collectors.toList());
    }

    public Category getCategoryName() {
        return this.categoryName;
    }

    public void setCategoryName(Category categoryName) {
        this.categoryName = categoryName;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Collection<QuestionDTO> getQuestions() {
        return this.questions;
    }

    public void setQuestions(Collection<QuestionDTO> questions) {
        this.questions = questions;
    }

    
    public String getCreatorName() {
        return this.creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }
}
