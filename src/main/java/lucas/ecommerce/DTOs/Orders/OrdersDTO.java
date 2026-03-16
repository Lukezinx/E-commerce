package lucas.ecommerce.DTOs.Orders;

import jakarta.validation.constraints.NotEmpty;
import lucas.ecommerce.DTOs.OrdersItems.OrdersItemsDTO;

import java.util.List;

public record OrdersDTO(
        @NotEmpty(message = "the list can't be empty")
        List<OrdersItemsDTO> items) {}
