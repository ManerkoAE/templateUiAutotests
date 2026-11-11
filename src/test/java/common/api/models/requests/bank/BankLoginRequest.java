package common.api.models.requests.bank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BankLoginRequest {

    /**
     * Имя пользователя. В JSON поле {@code username}.
     */
    private String username;

    /**
     * Пароль. В JSON поле {@code password}.
     */
    private String password;
}
