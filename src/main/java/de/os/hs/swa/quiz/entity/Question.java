package de.os.hs.swa.quiz.entity;

import java.util.Collection;

public class Question {
    private String text;
    private int questionNr;
    private Collection<Answer> answers;

    public Question() {
    }


    public Question(String text, int questionNr, Collection<Answer> answers) {
        this.text = text;
        this.questionNr = questionNr;
        this.answers = answers;
    }


    public String getText() {
        return this.text;
    }

    public int getQuestionNr() {
        return this.questionNr;
    }


    public Collection<Answer> getAnswers() {
        return this.answers;
    }





}
