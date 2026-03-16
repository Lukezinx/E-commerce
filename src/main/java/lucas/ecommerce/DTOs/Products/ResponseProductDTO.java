package lucas.ecommerce.DTOs.Products;

import lucas.ecommerce.DTOs.Category.CategoryResponseDTO;

import java.math.BigDecimal;
import java.util.UUID;

public record ResponseProductDTO(
        UUID id,
        String name,
        String SKU,
        String description,
        BigDecimal price,
        Integer quantityStock,
        CategoryResponseDTO category) {
}
