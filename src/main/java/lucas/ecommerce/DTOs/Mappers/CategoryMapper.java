package lucas.ecommerce.DTOs.Mappers;

import lucas.ecommerce.DTOs.Category.CategoryDTO;
import lucas.ecommerce.Model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mapping(target = "categoryName", source = "name")
    Category toEntity (CategoryDTO dto);

    @Mapping(target = "name", source = "categoryName")
    CategoryDTO categoryDTOToCategory(Category category);
}
