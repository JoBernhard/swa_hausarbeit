package de.os.hs.swa.quiz.entity;
import java.util.Collection;

import de.os.hs.swa.quiz.control.QuestionDTO;

public class QuizDTO {
    private String categoryName;
    private String title;
    private Collection<QuestionDTO> questions;

    public QuizDTO(String title, Collection<QuestionDTO> questions){
        this.title = title;
        this.questions = questions;
    }


    public QuizDTO() {
    }

    public QuizDTO(String categoryId, String title, Collection<QuestionDTO> questions) {
       
        this.categoryName = categoryId;
        this.title = title;
        this.questions = questions;
    }

    public String getCategoryName() {
        return this.categoryName;
    }

    public void setCategoryName(String categoryName) {
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
}
