package common.api.models.responses.bank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class BankUser {

    @JsonProperty

    private String id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private Double balance;
    private String accountNumber;
    private String createdAt;
}
