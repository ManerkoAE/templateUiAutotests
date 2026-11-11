package common.api.models.responses.bank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class BankTransfer {

    /** Успешность операции. */
    private Boolean success;
    /** Уникальный идентификатор транзакции. */
    private String id;

    /** ID пользователя. */
    private String userId;

    /** Сумма транзакции (отрицательная для расходов) */
    private Number amount;

    /** Тип транзакции */
    private String type;

    /** Дата и время транзакции */
    private String date;

    /** Описание транзакции. */
    private String description;

    /** Референс ID для связанных транзакций */
    private String referenceId;

    /** Комиссия за транзакцию */
    private Number fee;

    /** Имя отправителя (для входящих переводов). */
    private String senderName;

    /** Имя получателя (для исходящих переводов). */
    private String receiverName;

    /** Новый баланс отправителя. */
    private Number senderBalance;

    /** Новый баланс получателя. */
    private Number receiverBalance;
}
