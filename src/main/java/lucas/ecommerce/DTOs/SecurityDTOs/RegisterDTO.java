package lucas.ecommerce.DTOs.SecurityDTOs;

import lucas.ecommerce.Model.UserRoles;

public record RegisterDTO(String email, String password, UserRoles role) {
}
