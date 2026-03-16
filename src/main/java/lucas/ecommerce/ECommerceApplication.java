package lucas.ecommerce;

import lucas.ecommerce.Model.*;
import lucas.ecommerce.Repository.CategoryRepository;
import lucas.ecommerce.Repository.OrderItemsRepository;
import lucas.ecommerce.Repository.OrdersRepository;
import lucas.ecommerce.Repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootApplication
@EnableJpaAuditing
public class ECommerceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ECommerceApplication.class, args);

    }


//    @Bean
//    public CommandLineRunner exampleCommerceApplication(ProductRepository repository, CategoryRepository categoryRepository, OrdersRepository ordersRepository, OrderItemsRepository orderItemsRepository) {
//
//        return args -> {
//            Category category = new Category();
//            category.setCategoryName("teste");
//            categoryRepository.save(category);
//
//            BigDecimal price = new BigDecimal("100");
//            BigDecimal unitPrice = new BigDecimal("200");
//
//            Product product = new Product();
//            product.setName("teste2");
//            product.setDescription("ECommerce");
//            product.setPrice(price);
//            product.setQuantityStock(4);
//            product.setCategory(category);
//
//            repository.save(product);
//
//            Orders order = new Orders();
//            order.setCreationDate(LocalDate.now());
//            order.setOrderStatus(Status.PAID);
//
//            ordersRepository.save(order);
//
//            OrderItems orderItems = new OrderItems();
//            orderItems.setProduct(product);
//            orderItems.setOrder(order);
//            orderItems.setQuantity(4);
//            orderItems.setUnitPrice(unitPrice);
//
//            orderItemsRepository.save(orderItems);
//
//            System.out.println("The product has been added successfully");
//        };
//
//    }

}
