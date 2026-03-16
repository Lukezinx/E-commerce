package lucas.ecommerce.DTOs.Mappers;

import lucas.ecommerce.DTOs.Orders.OrdersResponseDTO;
import lucas.ecommerce.DTOs.OrdersItems.OrdersItemsResponseDTO;
import lucas.ecommerce.Model.OrderItems;
import lucas.ecommerce.Model.Orders;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrdersMapper {

    OrdersResponseDTO toResponseDTO(Orders orders);


    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "product.name", target = "productName")
    OrdersItemsResponseDTO toItemResponseDTO(OrderItems orderItems);

}
