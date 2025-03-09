package pages;
//import dataforInput.InputData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;



import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainPage extends AbsBasePage {




    public MainPage(WebDriver driver)  {
        super(driver, "/form.html");
    }



    private By languageLevelField = By.cssSelector("select[id='language_level']");

    private By languageLevelBeginer = By.xpath("//option[text()='Начальный']");
    private By languageLevelIntermediate = By.xpath("//option[text()='Средний']");
    private By languageLevelAdvanced = By.xpath("//option[text()='Продвинутый']");
    private By languageLevelNative = By.xpath("//option[text()='Носитель языка']");
    private By regstrsationBtn = By.cssSelector("input[value='Зарегистрироваться']");
    private By resultofRegistration = By.cssSelector("div[id='output']");

    private static String name = "Дмитрий";
    private static String email = "test@test.com";
    private static String password = "qwerty123";
    private static String birthdate = "111119992";

    private static String lvlLanguage = "Носитель языка";


    public enum InputField {
        USERNAME("input[id='username']",name),
        EMAIL("input[id='email']", email),
        PASSWORD("input[id='password']", password),
        CONFIRM_PASSWORD("input[id='confirm_password']", password),
        BIRTHDATE("input[id='birthdate']",birthdate);



        private final String locator;
        private final String data;

        InputField(String locator, String data) {
            this.locator = locator;
            this.data = data;
        }

        public By getLocator() {
            return By.cssSelector(locator);
        }

        public String getData() {
            return data;
        }
    }
    public void inputField(InputField field) {
        WebElement element = driver.findElement(field.getLocator());
        element.clear();
        element.sendKeys(field.getData());
    }



    public void languagelevel () {
        WebElement lvlList  = driver.findElement(languageLevelField);
        lvlList.click();
        WebElement lvl  = chooselvl(lvlLanguage);
        lvl.click();
    }

    public void registrationBtnClick () {
        WebElement registrationBtn  = driver.findElement(regstrsationBtn);
        registrationBtn.click();

    }

    public static String remapingBirthday(String bitrhday) {
        String day = bitrhday.substring(0, 2);
        String month = bitrhday.substring(2, 4);
        String year = bitrhday.substring(4);
        String remapDate = year + "-" + month + "-" + day;
        return remapDate;
    }

    public  WebElement chooselvl (String lvlLanguage) {
        WebElement lvlleng = null;
        switch (lvlLanguage) {

            case "Начальный":
                System.out.println("Выбран начальный уровень.");
                lvlleng = driver.findElement(languageLevelBeginer);
                break;
            case "Средний":
                System.out.println("Выбран средний уровень.");
                 lvlleng = driver.findElement(languageLevelIntermediate);
                break;
            case "Продвинутый":
                System.out.println("Выбран продвинутый уровень.");
                lvlleng = driver.findElement(languageLevelAdvanced);

                break;
            case "Носитель языка":
                System.out.println("Выбран уровень носителя языка.");
                lvlleng = driver.findElement(languageLevelNative);
                break;
            default:
                System.out.println("Уровень не распознан.");
                break;

        }
        return lvlleng;
    }


    public  String assertlvlleng (String lvlLenguage) {
        String lvlLenguageinEng = null;
        switch (lvlLenguage) {

            case "Начальный":
                lvlLenguageinEng = "beginner";
                break;
            case "Средний":
                lvlLenguageinEng = "intermediate";
                break;
            case "Продвинутый":
                lvlLenguageinEng = "advanced";
                break;
            case "Носитель языка":
                lvlLenguageinEng = "native";
                break;
            default:
                System.out.println("Уровень не распознан.");
                break;

        }
        return lvlLenguageinEng;
    }




    public void setResultOfRegistration () {


        WebElement outPutNameElement  = driver.findElement(resultofRegistration);
        String outPutNameText = outPutNameElement.getText();

        System.out.println(outPutNameText);


        String[] lines = outPutNameText.split("\n");
        String outputNameText = lines[0].trim();
        String outputEmailText = lines[1].trim();
        String outputBirthdayText = lines[2].trim();
        String outputLVLText = lines[3].trim();
        assertEquals("Имя пользователя: " + name, outputNameText, "Неверный текст имени");
        assertEquals( "Электронная почта: " + email, outputEmailText, "Неверный текст почты");
        assertEquals("Дата рождения: " + remapingBirthday(birthdate), outputBirthdayText, "Неверный текст даты рождения");
        assertEquals("Уровень языка: " + assertlvlleng(lvlLanguage), outputLVLText, "Неверный текст уровня языка");


    }


}
