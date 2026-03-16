package lucas.ecommerce.Repository.specs;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import lucas.ecommerce.Model.Category;
import lucas.ecommerce.Model.Product;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class ProductSpecs {

    public static Specification<Product> bySKU(String SKU) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("SKU"), SKU);
    }

    public static Specification<Product> nameLike(String name) {
        return ((root, query, cb) ->  cb.like(cb.upper(root.get("name")), "%" + name.toUpperCase() + "%"));
    }

    public static Specification<Product> maxPrice(BigDecimal price) {
        return (root, query, cb) -> {

            if (price == null) {
                return cb.conjunction();
            }

            return cb.lessThanOrEqualTo(root.get("price"), price);
        };
    }

    public static Specification<Product> categoryName(String category) {
        return ((root, query, criteriaBuilder) -> {

            if (category == null || category.isBlank()) {
                return criteriaBuilder.conjunction();
            }

            Join<Product, Category> join = root.join("category", JoinType.INNER);
            return criteriaBuilder.like(criteriaBuilder.upper(join.get("categoryName")), "%" + category.toUpperCase() + "%");
        });
    }
}
