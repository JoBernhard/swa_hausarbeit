package de.os.hs.swa.quiz.entity;

import java.util.Collection;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import de.os.hs.swa.category.entity.Category;

//@author: Johanna Bernhard

@Entity
public class Quiz {
    @Id @GeneratedValue 
    @Column(name = "quiz_id")
    private Long id;
    private String title;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "quiz", orphanRemoval = true)
    private Collection<Question> questions;
    private String creatorName;

    @ManyToOne 
    @JoinColumn(name = "category_name")
    private Category category;

    public Quiz(){}

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Collection<Question> getQuestions() {
        return this.questions;
    }

    public void setQuestions(Collection<Question> questions) {
        this.questions = questions;
    }
    

    public String getCreatorName() {
        return this.creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }


    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    

}
