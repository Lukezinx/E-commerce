package lucas.ecommerce.Repository;

import lucas.ecommerce.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository  extends JpaRepository<Category, Integer> {

    boolean existsByCategoryName(String categoryName);
}
