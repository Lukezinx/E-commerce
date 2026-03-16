package lucas.ecommerce.Model;


import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;


@Table(name = "order_items")
@Entity
@Data
public class OrderItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Orders order;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(nullable = false)
    private Integer quantity;

    @Column(name = "unit_price",nullable = false)
    private BigDecimal unitPrice;
}
