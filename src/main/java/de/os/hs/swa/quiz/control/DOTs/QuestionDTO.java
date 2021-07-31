package de.os.hs.swa.quiz.control.DOTs;

import java.util.Collection;

public class QuestionDTO {
    private String text;
    private Collection<AnswerDTO> answers;


    public QuestionDTO() {
    }

    public QuestionDTO(String text, Collection<AnswerDTO> answers) {
        this.text = text;
        this.answers = answers;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Collection<AnswerDTO> getAnswers() {
        return this.answers;
    }

    public void setAnswers(Collection<AnswerDTO> answers) {
        this.answers = answers;
    }

}
