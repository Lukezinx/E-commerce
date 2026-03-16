package lucas.ecommerce.Controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lucas.ecommerce.DTOs.Orders.OrdersDTO;
import lucas.ecommerce.DTOs.Orders.OrdersResponseDTO;
import lucas.ecommerce.Service.OrdersService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("orders")
@AllArgsConstructor
public class OrdersController {

    private OrdersService ordersService;

    @PostMapping
    public ResponseEntity<OrdersResponseDTO> createOrders(@Valid @RequestBody OrdersDTO dto) {

        OrdersResponseDTO saveOrder = ordersService.CreateOrders(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(saveOrder);
    }


    @GetMapping("{id}")
    public ResponseEntity<OrdersResponseDTO> getOrders(@PathVariable UUID id) {
        OrdersResponseDTO saveOrder = ordersService.findById(id);
        return ResponseEntity.ok(saveOrder);
    }


    @PatchMapping("{id}")
    public ResponseEntity<Void> updateOrders(@Valid @PathVariable UUID id) {
        ordersService.CancelOrders(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<OrdersResponseDTO>> listAllOrders (Pageable pageable) {
        Page<OrdersResponseDTO>  orders = ordersService.findAllOrders(pageable);
        return ResponseEntity.ok(orders);
    }


}
