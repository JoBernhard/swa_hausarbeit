package de.os.hs.swa.category.boundary;

import java.util.Collection;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import de.os.hs.swa.category.control.CategoryService;
import de.os.hs.swa.category.control.QuizForCategoryDTO;
import de.os.hs.swa.category.control.QuizService;
import de.os.hs.swa.category.entity.Category;

//@author: Johanna Benhard
//@author: Laura Peter

@RequestScoped
@Path("/category")
@Tag(name = "category", description = "get and edit info about categorys")
public class CategoryRessource {

    @Inject
    CategoryService categoryService;
    @Inject
    QuizService quizService;


    @GET @Path("/{categoryName}")
    @Operation(description = "get all Quizzes in Category")
    public Collection<QuizForCategoryDTO> getAllQuizzesInCategory(@Parameter(example = "Natur") @PathParam("categoryName") String category, @QueryParam("page") int page){
        return quizService.getAllQuizzes(category, page);
    }

    @GET
    @Operation(description = "get a Listing of all category Names")
    public Collection<String> getCategories(){
        return categoryService.getAllCategories();
    }

    @Transactional
    @POST @Path("/{categoryName}")
    @RolesAllowed("quiz-fest-category-admin")
    @Operation(description = "create a new category is only allowed for administrators")
    public Category addNewCategory(@Parameter(example = "NeueKategorie") @PathParam("categoryName") String category){
        return categoryService.addCategory(category);
    }

    @Transactional
    @DELETE @Path("/{categoryName}")
    @RolesAllowed("quiz-fest-category-admin")
    @Operation(description = "delete category by name is only allowed for administrator if category is empty")
    public Response deleteEmptyCategory(@Parameter(example = "NeueKategorie") @PathParam("categoryName")String category){
        if(categoryService.deleteCategoryByName(category)){
            return Response.noContent().build();
        }
        return Response.notModified("Category is Not empty").build();
    }
}
