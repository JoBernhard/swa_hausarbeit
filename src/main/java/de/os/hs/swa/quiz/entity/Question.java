package de.os.hs.swa.quiz.entity;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

//@author: Johanna Bernhard, Laura Peter

@Entity
public class Question {
    @Id @GeneratedValue 
    @Column(name = "question_id")
    private Long id;

    @NotBlank(message = "Questiontext shall not be blank")
    private String text;
    private int questionNr;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    @NotEmpty(message = "Question must contain Answers") @NotNull(message = "Answers shall not be null")
    private Collection<Answer> answers;
    @ManyToOne @JoinColumn(name="quiz_id", nullable = false)
    private Quiz quiz;

    public Question() {
    }

    public Question(String questionTitle, int questionNr, Collection<Answer> answers) {
        this.text = questionTitle;
        this.questionNr = questionNr;
        this.answers = answers;
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
