package lucas.ecommerce.Model;


public enum UserRoles {
    ADMIN("admin"),
    CLIENT("client");

    private final String roles;


    UserRoles(String roles) {
        this.roles = roles;
    }

    public String getRoles() {
        return roles;
    }

}
