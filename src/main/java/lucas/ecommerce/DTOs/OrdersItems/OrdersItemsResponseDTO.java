package lucas.ecommerce.DTOs.OrdersItems;

import java.math.BigDecimal;
import java.util.UUID;

public record OrdersItemsResponseDTO(UUID productId,
                                     String productName,
                                     Integer quantity,
                                     BigDecimal unitPrice) {
}
