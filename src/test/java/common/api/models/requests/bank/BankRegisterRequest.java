package common.api.models.requests.bank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BankRegisterRequest {

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
}
