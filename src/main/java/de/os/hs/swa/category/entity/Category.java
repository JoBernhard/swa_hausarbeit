package de.os.hs.swa.category.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

//@author: Laura Peter
@Entity
public class Category {
    @Id @Column(name = "category_name")
    private String name;
    

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
