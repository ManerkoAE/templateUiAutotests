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
        private Number usersCount;

        /** Подсчет количества транзакций.*/
        private Number transactionsCount;

        /** Подсчет количества оформленных кредитов.*/
        private Number loanOffersCount;

        /** Подсчет количества операций.*/
        private Number loanApplicationsCount;

        /** Подсчет количества кредитов.*/
        private Number loansCount;
}
