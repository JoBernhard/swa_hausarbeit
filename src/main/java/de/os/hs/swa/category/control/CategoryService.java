package de.os.hs.swa.category.control;

import java.util.Collection;

import de.os.hs.swa.category.entity.Category;

//@author: Johanna Bernhard
public interface CategoryService {
    public Collection<String> getAllCategories();
    public Category addCategory(String categoryName);
    public boolean deleteCategoryByName(String categoryName);
}
