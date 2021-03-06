package de.os.hs.swa.quiz.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

//@author: Johanna Bernhard, Laura Peter
@Entity
public class Answer {
    @Id @GeneratedValue 
    @Column(name = "answer_id")
    private Long id;

    @NotBlank(message = "Answertext shall not be blank")
    private String text;

    private int answerNr;

    private boolean isCorrect;
    
    @ManyToOne @JoinColumn(name="question_id", nullable = false)
    private Question question;

    public Answer(){
        
    }

    public Answer(String answerText, int answerNr, boolean isCorrect) {
        this.text = answerText;
        this.answerNr = answerNr;
        this.isCorrect = isCorrect;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getNumber() {
        return this.answerNr;
    }

    public void setNumber(int Number) {
        this.answerNr = Number;
    }

    public boolean isIsCorrect() {
        return this.isCorrect;
    }

    public boolean getIsCorrect() {
        return this.isCorrect;
    }

    public void setIsCorrect(boolean isCorrect) {
        this.isCorrect = isCorrect;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Question getQuestion() {
        return this.question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}
