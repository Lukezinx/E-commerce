package lucas.ecommerce.Controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lucas.ecommerce.DTOs.SecurityDTOs.AuthenticationDTO;
import lucas.ecommerce.DTOs.SecurityDTOs.LoginResponseDTO;
import lucas.ecommerce.DTOs.SecurityDTOs.RegisterDTO;
import lucas.ecommerce.Model.User;
import lucas.ecommerce.Repository.UserRepository;
import lucas.ecommerce.Security.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {


    private AuthenticationManager authenticationManager;
    private UserRepository repository;
    private TokenService tokenService;



    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthenticationDTO data) {

        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }


    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody @Valid RegisterDTO data) {

        if (this.repository.existsByEmail(data.email())) {
            return ResponseEntity.badRequest().build();
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());

        User newUser = new User();
        newUser.setEmail(data.email());
        newUser.setPassword(encryptedPassword);
        newUser.setRole(data.role());

        this.repository.save(newUser);

        return ResponseEntity.ok().build();
    }
}
