package de.os.hs.swa.category.gateway;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.NotFoundException;

import de.os.hs.swa.category.control.CategoryService;
import de.os.hs.swa.category.control.QuizForCategoryDTO;
import de.os.hs.swa.category.control.QuizService;
import de.os.hs.swa.category.entity.Category;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

//@author: Johanna Bernhard 
@RequestScoped
public class CategoryRepository implements QuizService, CategoryService, PanacheRepository<Category>{

    @Override
    public Collection<String> getAllCategories() {
        Stream<Category> categories = streamAll();
        return categories.map(c -> c.getName()).collect(Collectors.toList());
    }

    @Override
    public Category addCategory(String categoryName) {
        //TODO: check if name already in use
        Category category = new Category();
        category.setName(categoryName);
        persist(category);
        return category;
    }

    @Override
    public boolean deleteCategoryByName(String categoryName) {
        //TODO: check if category is empty
        if(getAllQuizzes(categoryName) != null){
            if(delete("name", categoryName)>0){
                return true;
            }
        }
        return false;
    }

    @Override
    public Collection<QuizForCategoryDTO> getAllQuizzes(String categoryName) {
        // TODO Auto-generated method stub
        Category category = find("name", categoryName).firstResult();
        if(category != null){

        }else{
            throw new NotFoundException(" Category "+category+" dosen't exist");
        }
        return null;
    }

    public boolean checkForCategory(String categoryName){
        return false;
    }
    
}
