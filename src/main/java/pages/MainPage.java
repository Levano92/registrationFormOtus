package pages;
//import dataforInput.InputData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import enums.InputField;




import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainPage extends AbsBasePage {




    public MainPage(WebDriver driver)  {
        super(driver, "/form.html");
        this.name = name;
        this.email = email;
        this.password = password;
        this.birthdate = birthdate;
    }



    private By languageLevelField = By.cssSelector("select[id='language_level']");

    private By regstrsationBtn = By.cssSelector("input[value='Зарегистрироваться']");
    private By resultofRegistration = By.cssSelector("div[id='output']");

    private String name = System.getProperty("name");
    private String email = System.getProperty("email");
    private String password = System.getProperty("password");
    private String birthdate = System.getProperty("birthdate");

    private  String lvlLanguage = "Носитель языка";

public void inputField(InputField field, String data) {
    WebElement element = driver.findElement(field.getLocator());
    element.clear();
    element.sendKeys(data);
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

    private WebElement chooselvl (String lvlLanguage) {

        return driver.findElement(By.xpath(String.format("//option[text()='%s']", lvlLanguage)));
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
