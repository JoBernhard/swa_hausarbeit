package de.os.hs.swa.quiz.control.DOTs;

import de.os.hs.swa.quiz.entity.Answer;

public class AnswerDTO {
    private String text;
    private boolean isCorrect;

    public AnswerDTO() {
    }

    public AnswerDTO(String text, boolean isCorrect) {
        this.text = text;
        this.isCorrect = isCorrect;
    }

    public AnswerDTO(Answer a){
        this.text = a.getText();
        this.isCorrect = a.isIsCorrect();
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
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
