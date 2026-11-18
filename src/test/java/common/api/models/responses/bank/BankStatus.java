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

        /** Статус системы банка.*/
        private String status;

        /** Подсчет количества пользователей.*/
        private Double usersCount;

        /** Подсчет количества транзакций.*/
        private Double transactionsCount;

        /** Подсчет количества оформленных кредитов.*/
        private Double loanOffersCount;

        /** Подсчет количества операций.*/
        private Double loanApplicationsCount;

        /** Подсчет количества кредитов.*/
        private Double loansCount;
}
