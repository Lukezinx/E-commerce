package lucas.ecommerce.DTOs.Products;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductDTO(
        @NotBlank(message = "product need a name")
        String name,
        @NotBlank(message = "product need a SKU")
        String SKU,
        String description,

        @NotNull(message = "The price can't be empty or negative")
        @Positive
        BigDecimal price,

        @NotNull(message = "The quantity stock can't be empty/black")
        @Min(0)
        Integer quantityStock,
        @NotBlank(message = "need to pass a category")
        Integer categoryId){
}
