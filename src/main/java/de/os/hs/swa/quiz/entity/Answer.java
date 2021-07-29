package de.os.hs.swa.quiz.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

//@author: Johanna Bernhard
@Entity
public class Answer {
    @Id @GeneratedValue private Long id;
    private String text;
    private int answerNr;
    private boolean isCorrect;


    public Answer(){
        
    }

    public Answer(String firtstAnswerText, int i, boolean b) {
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

}
