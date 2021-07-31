package de.os.hs.swa.quiz.acl;

public interface UserAdapter {
    public boolean isAuthorizedToEdit(String quizUsername);

    public String getCurrentUser();
}
