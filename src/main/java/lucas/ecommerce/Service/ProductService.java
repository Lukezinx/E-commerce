package lucas.ecommerce.Service;

import lombok.AllArgsConstructor;
import lucas.ecommerce.DTOs.Mappers.ProductMapper;
import lucas.ecommerce.DTOs.Products.ResponseProductDTO;
import lucas.ecommerce.Model.Category;
import lucas.ecommerce.Repository.specs.*;
import lucas.ecommerce.Model.Product;
import lucas.ecommerce.Repository.CategoryRepository;
import lucas.ecommerce.Repository.ProductRepository;
import lucas.ecommerce.Repository.specs.ProductSpecs;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static lucas.ecommerce.Repository.specs.ProductSpecs.*;


@Service
@AllArgsConstructor
public class ProductService {

    private ProductRepository repository;
    private CategoryRepository categoryRepository;
    private ProductMapper Mapper;


    public Product saveProduct(Product product) {
        if(repository.existsBySKU(product.getSKU())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "SKU already exists");
        }

         return  repository.save(product);
    }



    public Optional<Product> obtainProductId(UUID id) {
        return repository.findById(id);
    }

    public void updateProduct(Product product) {

        if(product.getId() == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "product does not exist/not registered");
        }

        repository.save(product);
    }


    public void deleteProduct(Product product) {
        if(product.getId() == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "product does not exist/not registered");
        }
        repository.delete(product);
    }


    public Page<ResponseProductDTO> searchProduct(String name, BigDecimal price, String SKU, String category, Pageable pageRequest) {

        Specification<Product> spec = Specification.where(Specification.where((root, query, cb) -> cb.conjunction()));

        if(name != null) {
            spec = spec.and(nameLike(name));
        }

        if(price != null) {
            spec = spec.and(maxPrice(price));
        }

        if(SKU != null) {
            spec = spec.and(bySKU(SKU));
        }

        if (category != null) {
            spec =  spec.and(categoryName(category));
        }

        return repository.findAll(spec,pageRequest).map(Mapper::toResponseDTO);

    }















}
