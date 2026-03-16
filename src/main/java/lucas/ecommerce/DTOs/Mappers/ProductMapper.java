package lucas.ecommerce.DTOs.Mappers;

import lucas.ecommerce.DTOs.Category.CategoryResponseDTO;
import lucas.ecommerce.DTOs.Products.ProductDTO;
import lucas.ecommerce.DTOs.Products.ResponseProductDTO;
import lucas.ecommerce.Model.Category;
import lucas.ecommerce.Model.Product;
import lucas.ecommerce.Repository.CategoryRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class ProductMapper {


    CategoryRepository repository;

    @Mapping(target = "category.id", source = "categoryId")
    public abstract Product toEntity(ProductDTO dto);


    public abstract ResponseProductDTO toResponseDTO(Product product);


    @Mapping(source = "categoryName", target = "name")
    public abstract CategoryResponseDTO toDTO(Category category);
}
