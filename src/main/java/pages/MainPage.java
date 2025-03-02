package pages;
import DataForInput.InputData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;



import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainPage extends AbsBasePage {


    public MainPage(WebDriver driver)  {
        super(driver, "/form.html");
    }

    private By nameField = By.cssSelector("input[id='username']");
    private By emailField = By.cssSelector("input[id='email']");
    private By passwordField = By.cssSelector("input[id='password']");
    private By passwordConfirmField = By.cssSelector("input[id='confirm_password']");
    private By birthdateField = By.cssSelector("input[id='birthdate']");
    private By languageLevelField = By.cssSelector("select[id='language_level']");

    private By languageLevelBeginer = By.xpath("//option[text()='Начальный']");
    private By languageLevelIntermediate = By.xpath("//option[text()='Средний']");
    private By languageLevelAdvanced = By.xpath("//option[text()='Продвинутый']");
    private By languageLevelNative = By.xpath("//option[text()='Носитель языка']");
    private By regstrsationBtn = By.cssSelector("input[value='Зарегистрироваться']");
    private By resultofRegistration = By.cssSelector("div[id='output']");

    public void inputText (String data, By locator) {
        WebElement element  = driver.findElement(locator);
        element.sendKeys(data);
    }

    public void inputName (){
        inputText(InputData.getName(), nameField);
    }
    public void inputEmail (){
        inputText(InputData.getEmail(), emailField);
    }

    public void inputPassword (){
        inputText(InputData.getPassword(), passwordField);
    }

    public void inputConfirmPassword () {
        inputText(InputData.getPassword(), passwordConfirmField);
    }

    public void inputBirthDate () {
        inputText(InputData.getBirthdate(), birthdateField);
    }

    public void lenguagelevel () {
        WebElement lvlList  = driver.findElement(languageLevelField);
        lvlList.click();
        WebElement lvl  = choosedvl(InputData.getLvlLanguage());
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

    public  WebElement choosedvl (String lvlLanguage) {
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
        assertEquals("Имя пользователя: " + InputData.getName(), outputNameText, "Неверный текст имени");
        assertEquals( "Электронная почта: " + InputData.getEmail(), outputEmailText, "Неверный текст почты");
        assertEquals("Дата рождения: " + remapingBirthday(InputData.getBirthdate()), outputBirthdayText, "Неверный текст даты рождения");
        assertEquals("Уровень языка: " + assertlvlleng(InputData.getLvlLanguage()), outputLVLText, "Неверный текст уровня языка");


    }


}
