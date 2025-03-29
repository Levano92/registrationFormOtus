package enums;
import org.openqa.selenium.By;
public enum InputField {
    USERNAME("input[id='username']"),
    EMAIL("input[id='email']"),
    PASSWORD("input[id='password']"),
    CONFIRM_PASSWORD("input[id='confirm_password']"),
    BIRTHDATE("input[id='birthdate']");

    private final String locator;

    InputField(String locator) {
        this.locator = locator;
    }

    public By getLocator() {
        return By.cssSelector(locator);
    }
}
