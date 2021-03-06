package de.os.hs.swa.category.gateway;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import de.os.hs.swa.category.control.CategoryService;
import de.os.hs.swa.category.control.QuizForCategoryDTO;
import de.os.hs.swa.category.control.QuizService;
import de.os.hs.swa.category.entity.Category;
import de.os.hs.swa.quiz.entity.Quiz;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

//@author: Johanna Bernhard 
@RequestScoped
public class CategoryRepository implements QuizService, CategoryService, PanacheRepository<Category>{

    @Inject
    PanacheRepository<Quiz> quizRepository;

    @ConfigProperty(name="page.size")
    Integer pageSize;


    @Override
    public Collection<String> getAllCategories() {
        Stream<Category> categories = streamAll();
        return categories.map(c -> c.getName()).collect(Collectors.toList());
    }

    @Override
    public Category addCategory(String categoryName) {
        if(find("category_name", categoryName).firstResult() == null){
            Category category = new Category();
            category.setName(categoryName);
            persist(category);
            return category;
        } else {
            throw new BadRequestException("Category already exists");
        }
        
    }

    @Override
    public boolean deleteCategoryByName(String categoryName) {
        Collection<Quiz> quizzes = quizRepository.find("category_name", categoryName).list();
        if(quizzes != null && quizzes.isEmpty()){
            if(delete("category_name", categoryName)>0){
                return true;
            }
        }
        return false;
    }

    @Override
    public Collection<QuizForCategoryDTO> getAllQuizzes(String categoryName, int page) {
        Category category = find("category_name", categoryName).firstResult();
        if(category != null){
            Collection<QuizForCategoryDTO> quizzes;
            PanacheQuery<Quiz> categoryQuizes = quizRepository.find("category_name", categoryName);
            quizzes = categoryQuizes.page(page, pageSize).stream()
            .map(q -> quizToDTO(q)).collect(Collectors.toList());
            return quizzes;

        }else{
            throw new NotFoundException(" Category "+category+" dosen't exist");
        }
    }


    private QuizForCategoryDTO quizToDTO(Quiz q){
        QuizForCategoryDTO dto = new QuizForCategoryDTO();
        dto.linkToQuiz = "quizzes/"+q.getId()+"/play";
        dto.title = q.getTitle();

        return dto;
    }
}
