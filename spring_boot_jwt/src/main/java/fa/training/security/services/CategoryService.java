package fa.training.security.services;

import fa.training.models.Category;
import fa.training.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category createCategory(Category category) {

        return categoryRepository.save(category);
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId).orElse(null);
    }

    public Category updateCategory(Long categoryId, Category updatedCategory) {
        Category existingCategory = categoryRepository.findById(categoryId).orElse(null);

        if (existingCategory != null) {
            // Update fields based on your requirements
            existingCategory.setName(updatedCategory.getName());

            return categoryRepository.save(existingCategory);
        } else {
            return null; // Handle the case where the category with given ID doesn't exist
        }
    }

    public void deleteCategory(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }
}
