package lucas.ecommerce.Controller;

import lombok.AllArgsConstructor;
import lucas.ecommerce.DTOs.Category.CategoryDTO;
import lucas.ecommerce.DTOs.Category.CategoryResponseDTO;
import lucas.ecommerce.DTOs.Mappers.CategoryMapper;
import lucas.ecommerce.Model.Category;
import lucas.ecommerce.Service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/category")
@AllArgsConstructor
public class CategoryController {

    private CategoryService categoryService;
    private CategoryMapper mapper;

    @PostMapping
    public ResponseEntity<Object> saveCategory(@RequestBody CategoryDTO category) {
        Category categoryMapper = mapper.toEntity(category);
        categoryService.saveCategory(categoryMapper);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(categoryMapper.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> updateCategory(@PathVariable Integer id, @RequestBody CategoryDTO categoryDto) {
        Optional<Category> categoryOptional = categoryService.getById(id);

        if (categoryOptional.isEmpty()) {
           return ResponseEntity.notFound().build();
        }

        var category = categoryOptional.get();
        category.setCategoryName(categoryDto.name());

        categoryService.updateCategory(category);

        return ResponseEntity.noContent().build();

    }


    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Integer id) {
        Optional<Category> categoryOptional = categoryService.getById(id);

        if (categoryOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        categoryService.deleteCategory(categoryOptional.get());
        return ResponseEntity.noContent().build();
    }


    @GetMapping
    public ResponseEntity<List<CategoryResponseDTO>> categoryList(){
        List<Category> categoryList = categoryService.getAllCategories();

        List<CategoryResponseDTO> categoryResponseDTOList = categoryList.stream().map(CategoryResponseDTO::fromEntity).toList();
        return ResponseEntity.ok(categoryResponseDTOList);
    }

}
