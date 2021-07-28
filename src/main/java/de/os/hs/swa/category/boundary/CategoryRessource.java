package de.os.hs.swa.category.boundary;

import java.util.Collection;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import de.os.hs.swa.category.entity.Category;

@RequestScoped
@Path("/category")
@Tag(name = "category", description = "get and edit info about categorys")
public class CategoryRessource {
    
    @GET @Path("/{categoryName}")
    @Operation(description = "get all Quizzes in Category")
    public Response getAllQuizzesInCategory(@PathParam("categoryName") String category){
        return null;
    }

    @GET
    @Operation(description = "get a Listing of all availabel categorys")
    public Collection<Category> getCategories(){
        return null;
    }

    @POST
    @Operation(description = "create a new category is only allowed for administrators")
    public Response addNewCategory(@QueryParam("categoryName") String category){
        return null;
    }

    @DELETE
    @Operation(description = "delete category by name is only allowed for administrator if category is empty")
    public Response deleteEmptyCategory(@QueryParam("categoryName")String category){
        return null;
    }
}
