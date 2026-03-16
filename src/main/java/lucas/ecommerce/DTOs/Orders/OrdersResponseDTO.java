package lucas.ecommerce.DTOs.Orders;

import lucas.ecommerce.DTOs.OrdersItems.OrdersItemsResponseDTO;
import lucas.ecommerce.Model.Status;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record OrdersResponseDTO(UUID id,
                                LocalDate creationDate,
                                Status orderStatus,
                                List<OrdersItemsResponseDTO> items) {
}
