package de.os.hs.swa.quiz.control.DOTs;

public class AnswerDTO {
    private String text;
    private int answerNr;
    private boolean isCorrect;

    public AnswerDTO() {
    }


    public AnswerDTO(String text, int answerNr, boolean isCorrect) {
        this.text = text;
        this.answerNr = answerNr;
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
