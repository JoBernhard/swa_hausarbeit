package de.os.hs.swa.quiz.entity;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

//@author: Johanna Bernhard

@Entity
public class Question {
    @Id @GeneratedValue private Long id;
    private String text;
    private int questionNr;
    private Collection<Answer> answers;


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

}
