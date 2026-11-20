package common.api.models.responses.bank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class BankStatus {

    private String status;
    private Double usersCount;
    private Double transactionsCount;
    private Double loanOffersCount;
    private Double loanApplicationsCount;
    private Double loansCount;
}
