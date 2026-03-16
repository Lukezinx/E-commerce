package lucas.ecommerce.DTOs.Products;

import java.math.BigDecimal;

public record ProductDTO(
        String name,
        String SKU,
        String description,
        BigDecimal price,
        Integer quantityStock,
        Integer categoryId){
}
