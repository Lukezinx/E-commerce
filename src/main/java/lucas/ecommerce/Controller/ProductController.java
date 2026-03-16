package lucas.ecommerce.Controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lucas.ecommerce.DTOs.Mappers.ProductMapper;
import lucas.ecommerce.DTOs.Products.ProductDTO;
import lucas.ecommerce.DTOs.Products.ResponseProductDTO;
import lucas.ecommerce.Model.Category;
import lucas.ecommerce.Model.Product;
import lucas.ecommerce.Service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/product")
@AllArgsConstructor
public class ProductController {

    private ProductService productService;
    private ProductMapper mapper;


    @PostMapping
    public ResponseEntity<Object> saveProduct(@Valid @RequestBody ProductDTO productDTO) {
        Product product = mapper.toEntity(productDTO);
        productService.saveProduct(product);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(product.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> updateProduct(@Valid @PathVariable String id,@RequestBody ProductDTO productDTO) {
            return productService.obtainProductId(UUID.fromString(id)).map(product -> {
                Product productAux = mapper.toEntity(productDTO);

                product.setName(productAux.getName());
                product.setSKU(productAux.getSKU());
                product.setPrice(productAux.getPrice());
                product.setDescription(productAux.getDescription());
                product.setQuantityStock(productAux.getQuantityStock());
                product.setCategory(productAux.getCategory());

                productService.updateProduct(product);

                return ResponseEntity.noContent().build();

            }).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteProduct(@Valid @PathVariable String id) {
        Optional<Product> productOptional = productService.obtainProductId(UUID.fromString(id));
        if (productOptional.isEmpty()) return ResponseEntity.notFound().build();

        var product  = productOptional.get();
        productService.deleteProduct(product);
        return ResponseEntity.noContent().build();
    }


    @GetMapping
    public ResponseEntity<Page<ResponseProductDTO>> search(
            @RequestParam(required = false)
            String name,
            @RequestParam(required = false)
            BigDecimal price,
            @RequestParam(required = false)
            String SKU,
            @RequestParam(required = false)
            String category,
            Pageable page) {

        Page<ResponseProductDTO> filterPage = productService.searchProduct(name, price, SKU, category, page);
        return ResponseEntity.ok(filterPage);
    }

























}
