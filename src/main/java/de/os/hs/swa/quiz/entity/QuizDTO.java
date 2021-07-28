package de.os.hs.swa.quiz.entity;
import java.util.Collection;

public class QuizDTO {
    private String categoryName;
    private String title;
    private Collection<Question> questions;

    public QuizDTO(String title, Collection<Question> questions){
        this.title = title;
        this.questions = questions;
    }


    public QuizDTO() {
    }

    public QuizDTO(String categoryId, String title, Collection<Question> questions) {
       
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

    public Collection<Question> getQuestions() {
        return this.questions;
    }

    public void setQuestions(Collection<Question> questions) {
        this.questions = questions;
    }
}
