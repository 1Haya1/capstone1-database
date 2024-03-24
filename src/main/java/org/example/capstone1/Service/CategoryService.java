package org.example.capstone1.Service;

import lombok.RequiredArgsConstructor;
import org.example.capstone1.Model.Category;
import org.example.capstone1.Reopsitory.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class CategoryService {


  private final CategoryRepository categoryRepository;


    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }


    public void addCategory(Category category) {
        categoryRepository.save(category);
    }

    public Boolean updateCategory(Integer id, Category category) {
        Category c=categoryRepository.getById(id);
        if(c==null){
            return false;
        }
       c.setName(category.getName());
        categoryRepository.save(c);
        return true;
    }

    public Boolean deleteCategory(Integer id) {
        Category c=categoryRepository.getById(id);
        if(c==null){
            return false;
        }
        categoryRepository.delete(c);
        return true;
    }
}

