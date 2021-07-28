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

import de.os.hs.swa.category.entity.Category;

@RequestScoped
@Path("/category")
public class CategoryRessource {
    
    @GET @Path("/{categoryName}")
    public Response getAllQuizzesInCategory(@PathParam("categoryName") String category){
        return null;
    }

    @GET
    public Collection<Category> getCategories(){
        return null;
    }

    @POST
    public Response addNewCategory(@QueryParam("categoryName") String category){
        return null;
    }

    @DELETE
    public Response deleteEmptyCategory(@QueryParam("categoryName")String category){
        return null;
    }
}
