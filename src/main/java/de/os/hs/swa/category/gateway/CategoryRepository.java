package de.os.hs.swa.category.gateway;

import java.util.Collection;

import javax.enterprise.context.RequestScoped;

import de.os.hs.swa.category.control.CategoryService;
import de.os.hs.swa.category.control.QuizForCategoryDTO;
import de.os.hs.swa.category.control.QuizService;
import de.os.hs.swa.category.entity.Category;

@RequestScoped
public class CategoryRepository implements QuizService, CategoryService{

    @Override
    public Collection<String> getAllCategories() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Category addCategory(Category category) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean deleteCategory() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Collection<QuizForCategoryDTO> getAllQuizzes(String category) {
        // TODO Auto-generated method stub
        return null;
    }

    public boolean checkForCategory(String categoryName){
        return false;
    }
    
}
