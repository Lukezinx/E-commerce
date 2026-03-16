package lucas.ecommerce.DTOs.Category;

import lucas.ecommerce.Model.Category;

public record CategoryResponseDTO(Integer id, String name){


    public static CategoryResponseDTO fromEntity(Category category){
        return new CategoryResponseDTO(
                category.getId(),
                category.getCategoryName()
        );
    }
}
