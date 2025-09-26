package Journal.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data

public class AuthResponse {
    private String token;
    private String username;

     public AuthResponse(String token, String username) {
        this.token = token;
        this.username = username;
    }
    
}
