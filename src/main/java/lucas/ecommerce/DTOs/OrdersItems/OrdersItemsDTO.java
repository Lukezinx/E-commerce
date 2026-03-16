package lucas.ecommerce.DTOs.OrdersItems;

import jakarta.validation.constraints.Min;

import java.beans.XMLEncoder;
import java.util.UUID;

public record OrdersItemsDTO(
        UUID productId,
        @Min(value = 1, message = "the minimum is one")
        Integer quantity
) {
}
