package DataForInput;

public class InputData {
    private static String name = System.getProperty("name", "Тестовое имя"); // Значение по умолчанию "Дима"
    private static String email = System.getProperty("email", "test@test.ru");
    private static String password = System.getProperty("password", "test123");
    private static String birthdate = System.getProperty("birthdate", "21051998");
    private static String lvlLanguage = System.getProperty("lvlLanguage", "Носитель языка");

     public static String getName() {
        return name;
    }

    public static String getEmail() {
        return email;
    }

    public static String getPassword() {
        return password;
    }

    public static String getBirthdate() {
        return birthdate;
    }

    public  static String getLvlLanguage() {
        return lvlLanguage;
    }
}