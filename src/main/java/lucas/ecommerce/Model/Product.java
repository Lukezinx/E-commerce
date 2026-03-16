package lucas.ecommerce.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "product")
@Data
public class Product {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String SKU;

    @Column
    private String description;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(name = "quantity_stock",nullable = false)
    private Integer quantityStock;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
}
