# templateUiAutotests

Портфолио проекта, демонстрирующее эффективную автоматизацию тестов с параметризованными тестами для UI-приложений на основе Selenium и Allure.

## Особенности
- **Параметризованные тесты**:
  - Использование `@ValueSource` для проверки различных доменов при регистрации (mail.ru, gmail.com, yandex.ru, rambler.ru, outlook.com)
  - Проверка валидации формата email через `@EnumSource` (NO_AT_SYMBOL, NO_DOMAIN, DOUBLE_AT, SPACE_INSIDE)
- **Конфигурация**:
  - Использование Lombok и Maven для управления зависимостями
  - Структура проекта с разделением на страницы и тесты
- **Отчеты**:
  - Всё содержимое тестов генерирует отчеты в Allure

## Как запустить тесты
1. Убедитесь, что установлены зависимости через Maven
2. Запустите тесты:
```bash
mvn clean test
```

## Примеры тестов

**Позитивный тест**:
```java
@Story("Регистрация с валидным доменом")
@Description("Проверка регистрации при использовании домена mail.ru")
public class RegistrationDomainValueSourceTest {
    // Тестовый код с параметризацией через ValueSource
}
```

**Негативный тест**:
```java
@Story("Регистрация с невалидным email")
@Description("Проверка валидации формата email")
public class RegistrationEmailEnumSourceTest {
    // Тестовый код с параметризацией через EnumSource
}
```

## Документация
Полная документация проекта доступна в:
- [Код](https://github.com/yourusername/templateUiAutotests)
- [Отчеты Allure](target/allure-results)

