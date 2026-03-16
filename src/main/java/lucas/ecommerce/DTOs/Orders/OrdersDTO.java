package lucas.ecommerce.DTOs.Orders;

import lucas.ecommerce.DTOs.OrdersItems.OrdersItemsDTO;

import java.util.List;

public record OrdersDTO(List<OrdersItemsDTO> items) {}
