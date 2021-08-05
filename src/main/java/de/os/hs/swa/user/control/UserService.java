package de.os.hs.swa.user.control;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import de.os.hs.swa.quiz.acl.UserAdapter;
import io.quarkus.security.ForbiddenException;
import io.quarkus.security.UnauthorizedException;
import io.quarkus.security.identity.SecurityIdentity;

//@author: Laura Peter
@RequestScoped
public class UserService implements UserAdapter{

    @Inject
    SecurityIdentity identity;

    //checks if username from principla equals the username saved in the quiz
    @Override
    public boolean isAuthorizedToEdit(String quizUsername){
        String username = identity.getPrincipal().getName();
        if(quizUsername.equals("")){
            throw new UnauthorizedException("No Usercredentials found");
        }
        if(!quizUsername.equals(username)){
            throw new ForbiddenException("Username doesn't equal the Quiz's creator name");
        }

        return true;
    }

    @Override
    public String getCurrentUser() {
        return identity.getPrincipal().getName();
    }
    
}
