package common.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Загрузчик конфигурации для тестов
 */
public class ConfigLoader {

    private static final Properties properties = new Properties();

    static {
        try (InputStream input = ConfigLoader.class.getClassLoader()
                .getResourceAsStream("config.properties")) {

            if (input == null) {
                throw new RuntimeException(
                        "❌ Файл config.properties не найден в папке src/test/resources/\n" +
                                "Создай файл со следующими параметрами:\n" +
                                "login=login\n" +
                                "password=password\n" +
                                "baseBankurl=https://demoqa.ru/bank"
                );
            }

            properties.load(input);
            System.out.println("✅ Конфигурация успешно загружена");

        } catch (IOException e) {
            throw new RuntimeException("❌ Ошибка при загрузке config.properties: " + e.getMessage(), e);
        }
    }

    /**
     * Получить значение свойства
     */
    public static String get(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            throw new RuntimeException("❌ Свойство '" + key + "' не найдено в config.properties");
        }
        return value;
    }

    /**
     * Получить значение свойства со значением по умолчанию
     */
    public static String get(String key, String defaultValue) {
        String value = properties.getProperty(key);
        return value != null ? value : defaultValue;
    }

    /**
     * Получить целочисленное значение свойства
     */
    public static int getInt(String key) {
        String value = get(key);
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new RuntimeException("❌ Свойство '" + key + "' должно быть числом, но получено: " + value);
        }
    }
}