package lseg.example.prueba.security.jwt.models;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Clase DTO para las peticiones relacionadas a la generación del Token")
public class AuthRequest {
    @Schema(description = "Contraseña", example = "admin123")
    private String password;
    @Schema(description = "Usuario", example = "admin")
    private String username;
}
