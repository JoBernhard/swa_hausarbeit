package de.os.hs.swa.quiz.entity;

public class Answer {
    private String text;
    private int answerNr;
    private boolean isCorrect;


    public Answer(String text, int Number, boolean isCorrect) {
        this.text = text;
        this.answerNr = Number;
        this.isCorrect = isCorrect;
    }


    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getAnswerNr() {
        return this.answerNr;
    }

    public void setAnswerNr(int answerNr) {
        this.answerNr = answerNr;
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


}
