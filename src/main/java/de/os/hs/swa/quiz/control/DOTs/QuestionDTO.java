package de.os.hs.swa.quiz.control.DOTs;

import java.util.Collection;

public class QuestionDTO {
    private String text;
    private int questionNr;
    private Collection<AnswerDTO> answers;


    public QuestionDTO() {
    }

    public QuestionDTO(String text, int questionNr, Collection<AnswerDTO> answers) {
        this.text = text;
        this.questionNr = questionNr;
        this.answers = answers;
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

    public Collection<AnswerDTO> getAnswers() {
        return this.answers;
    }

    public void setAnswers(Collection<AnswerDTO> answers) {
        this.answers = answers;
    }

}
