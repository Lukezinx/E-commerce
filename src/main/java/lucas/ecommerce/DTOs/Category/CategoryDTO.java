package lucas.ecommerce.DTOs.Category;

import jakarta.validation.constraints.NotBlank;

public record CategoryDTO(
        @NotBlank(message = "Category name required")
        String name){


}
