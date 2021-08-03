package de.os.hs.swa.category.control;

import java.util.Collection;

public interface QuizService {
    public Collection<QuizForCategoryDTO> getAllQuizzes(String category, int page); 
}
