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

    /** ID отправителя.*/
    private String fromUserId;

    /** Получатель (username или номер счета).*/
    private String recipient;

    /** Сумма перевода.*/
    private Double amount;

    /** Флаг: true если recipient - номер счета, false если username.*/
    private Boolean isAccountNumber;
}
