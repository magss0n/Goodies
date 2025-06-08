package cm.sji.goodies.Model.Services;

import cm.sji.goodies.Model.Entities.Category;

import java.util.List;

public interface CategoryService {
    Category createCategory(Category category);
    Category getCategory(Long id);
    List<Category> getAllCategories();
    Category updateCategory(Category category);
    Category getCategoryByName(String name);
}
