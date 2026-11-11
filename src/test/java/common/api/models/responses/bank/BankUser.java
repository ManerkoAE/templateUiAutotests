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
public class BankUser {

    /**
     * Идентификатор пользователя.
     */
    private String id;

    /**
     * Имя пользователя.
     */
    private String username;

    /**
     * Имя.
     */
    private String firstName;

    /**
     * Фамилия.
     */
    private String lastName;

    /**
     * Email.
     */
    private String email;

    /**
     * Баланс счёта.
     */
    private Double balance;

    /**
     * Номер банковского счёта.
     */
    private String accountNumber;

    /**
     * Дата создания учётной записи, как строка из JSON.
     */
    private String createdAt;

}
