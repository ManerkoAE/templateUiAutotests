package common.api.models.requests.bank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BankTransferRequest {

    private String fromUserId;
    private String recipient;
    private Double amount;
    private Boolean isAccountNumber;
}
