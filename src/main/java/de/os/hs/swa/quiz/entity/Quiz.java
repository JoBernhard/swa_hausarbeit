package de.os.hs.swa.quiz.entity;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import de.os.hs.swa.category.entity.Category;

//@author: Johanna Bernhard

@Entity
public class Quiz {
    @Id @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "quiz_id")
    private Long id;

    @NotBlank(message = "Quiz Title shall not be blank")
    private String title;

    @NotBlank(message = "Creatorname shall not be blank")
    private String creatorName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "quiz", orphanRemoval = true)
    @NotEmpty(message = "Quiz must contain questions") @NotNull(message = "Questions shall not be null")
    private Collection<Question> questions;

    @ManyToOne 
    @JoinColumn(name = "category_name")
    @NotNull(message = "Category shall not be null")
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
