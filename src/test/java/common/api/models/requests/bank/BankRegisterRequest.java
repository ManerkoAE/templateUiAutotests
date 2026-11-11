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

    /** Логин пользователя. В JSON поле {@code username}. */
    private String username;

    /** Пароль. В JSON поле {@code password}. */
    private String password;

    /** Имя пользователя. В JSON поле {@code firstname}. */
    private String firstName;

    /** Фамилия. В JSON поле {@code lastname}. */
    private String lastName;

    /** Почта. В JSON поле {@code email}. */
    private String email;
}
