package de.os.hs.swa.quiz.acl;

//@author: Johanna Bernhard
public interface UserAdapter {
    public boolean isAuthorizedToEdit(String quizUsername);

    public String getCurrentUser();
}
