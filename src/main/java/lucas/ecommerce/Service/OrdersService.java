package lucas.ecommerce.Service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lucas.ecommerce.DTOs.Mappers.OrdersMapper;
import lucas.ecommerce.DTOs.Orders.OrdersDTO;
import lucas.ecommerce.DTOs.Orders.OrdersResponseDTO;
import lucas.ecommerce.DTOs.OrdersItems.OrdersItemsDTO;
import lucas.ecommerce.Model.*;
import lucas.ecommerce.Repository.OrdersRepository;
import lucas.ecommerce.Repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.UUID;

@Service
@AllArgsConstructor
public class OrdersService {

    private OrdersRepository ordersRepository;
    private ProductRepository productRepository;
    private OrdersMapper ordersMapper;


    @Transactional
    public OrdersResponseDTO CreateOrders(OrdersDTO request) {
        Orders orders = new Orders();
        orders.setCreationDate(LocalDate.now());
        orders.setOrderStatus(Status.PENDING);


        var authentication = SecurityContextHolder.getContext().getAuthentication();
        User loggedUser = (User) authentication.getPrincipal();

        if (loggedUser == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        orders.setUser(loggedUser);

        for (OrdersItemsDTO items : request.items()) {

            Product product = productRepository.findById(items.productId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found/Not exist"));

            OrderItems orderItems = new OrderItems();
            orderItems.setProduct(product);



            if(items.quantity() > product.getQuantityStock()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product quantity out of stock");
            } else {
                var quantity = product.getQuantityStock() - items.quantity();
                product.setQuantityStock(quantity);
            }

            orderItems.setQuantity(items.quantity());
            orderItems.setUnitPrice(product.getPrice());

            orderItems.setOrder(orders);

            orders.getItems().add(orderItems);


        }

        Orders savedOrders = ordersRepository.save(orders);

        return ordersMapper.toResponseDTO(savedOrders);

    }

    public OrdersResponseDTO findById(UUID id){
        Orders order = ordersRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found"));
        return ordersMapper.toResponseDTO(order);
    }



    @Transactional
    public void CancelOrders(UUID id){
        Orders order =  ordersRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found"));

        if (order.getOrderStatus().equals(Status.CANCELLED)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This order is already cancelled");
        }

        order.setOrderStatus(Status.CANCELLED);
        ordersRepository.save(order);
    }

    public Page<OrdersResponseDTO> findAllOrders(Pageable pageable) {
        Page<Orders> ordersPage = ordersRepository.findAll(pageable);
        return ordersPage.map(ordersMapper::toResponseDTO);

    }




















}
