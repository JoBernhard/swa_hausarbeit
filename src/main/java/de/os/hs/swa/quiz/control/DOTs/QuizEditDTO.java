package de.os.hs.swa.quiz.control.DOTs;
import java.util.Collection;

import de.os.hs.swa.category.entity.Category;

//@author: Laura Peter

public class QuizEditDTO {
    private Category categoryName;
    private String title;
    private Collection<QuestionDTO> questions;

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
