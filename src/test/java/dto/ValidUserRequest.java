package dto;

import lombok.*;

@Setter@Getter@AllArgsConstructor@NoArgsConstructor@Builder
public class ValidUserRequest {
    private String full_name;
    private String email;
    private String password;
    private String extractUserId;
    private boolean generate_magic_link;

}