package de.os.hs.swa.quiz.entity;

import java.util.Collection;

public class Quiz {
    private Long id;
    private String categoryId;
    private String title;
    private Collection<Question> questions;

    public Quiz(String title, Collection<Question> questions){
        this.title = title;
        this.questions = questions;
    }
    
}
