package lucas.ecommerce.Service;

import lombok.AllArgsConstructor;
import lucas.ecommerce.Model.Category;
import lucas.ecommerce.Repository.CategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryService {


    private CategoryRepository categoryRepository;


    public void saveCategory(Category category){
        if (categoryRepository.existsByCategoryName(category.getCategoryName())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Category already exists");
        }
        categoryRepository.save(category);
    }


    public void updateCategory(Category category){
        if (category.getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The catrgory don't exists");
        }
        categoryRepository.save(category);
    }

    public Optional<Category> getById(Integer id){
        return categoryRepository.findById(id);
    }



    public void deleteCategory(Category category){
        if (categoryRepository.existsById(category.getId())) {
            categoryRepository.deleteById(category.getId());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found");
        }

    }

    public List<Category> getAllCategories(){
       return  categoryRepository.findAll();
    }

}
