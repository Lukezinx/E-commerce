package lucas.ecommerce.DTOs.OrdersItems;

import java.util.UUID;

public record OrdersItemsDTO(
        UUID productId,
        Integer quantity
) {
}
