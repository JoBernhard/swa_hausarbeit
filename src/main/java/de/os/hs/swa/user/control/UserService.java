package de.os.hs.swa.user.control;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.core.Response;

import de.os.hs.swa.quiz.acl.UserAdapter;
import io.quarkus.security.ForbiddenException;
import io.quarkus.security.identity.SecurityIdentity;

@RequestScoped
public class UserService implements UserAdapter{

    @Inject
    SecurityIdentity identity;

    @Override
    public boolean isAuthorizedToEdit(String quizUsername){
        String username = identity.getPrincipal().getName();
        if(quizUsername.equals("")){
            Response response = Response.status(Response.Status.UNAUTHORIZED).entity("Invalid credentials or session").build();
            throw new NotAuthorizedException(response);
        }
        if(!quizUsername.equals(username)){
            throw new ForbiddenException();
        }

        return true;
    }

    @Override
    public String getCurrentUser() {
        return identity.getPrincipal().getName();
    }
    
}
