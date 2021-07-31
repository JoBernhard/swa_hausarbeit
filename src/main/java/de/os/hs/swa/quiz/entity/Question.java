package de.os.hs.swa.quiz.entity;

import java.util.Collection;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

//@author: Johanna Bernhard

@Entity
public class Question {
    @Id @GeneratedValue 
    @Column(name = "question_id")
    private Long id;
    private String text;
    private int questionNr;
    @Transient
    private Collection<Answer> answers;
    @ManyToOne @JsonbTransient
    private Quiz quiz;

    public Question() {
    }

    public Question(String questionTitle, int i, Collection<Answer> answers) {
    }


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getQuestionNr() {
        return this.questionNr;
    }

    public void setQuestionNr(int questionNr) {
        this.questionNr = questionNr;
    }


    public Collection<Answer> getAnswers() {
        return this.answers;
    }

    public void setAnswers(Collection<Answer> answers) {
        this.answers = answers;
    }

    public Quiz getQuiz() {
        return this.quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

}
