package de.os.hs.swa.category.gateway;

import javax.inject.Inject;

import de.os.hs.swa.category.entity.Category;
import de.os.hs.swa.quiz.acl.CategoryAdapter;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

public class CategoryAdapterImpl implements CategoryAdapter {

    @Inject
    PanacheRepository<Category> categoryRepository;

    @Override
    public String checkForCategory(String categoryName) {
        Category category = categoryRepository.find("name", categoryName).firstResult();
        if(category != null){
            return category.getName();
        }else{
            return null;
        }
        
    }
    
}
