package main;

import factory.WebDriverFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.MainPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static enums.InputField.*;

//import static pages.MainPage.InputField.*;


public class MainPage_Test {
    private static final Logger logger = LogManager.getLogger(MainPage_Test.class);
    private final String name = System.getProperty("name");
    private final String email = System.getProperty("email");
    private final String password = System.getProperty("password");
    private final String birthdate = System.getProperty("birthdate");
    private static WebDriverFactory webDriverFactory = new WebDriverFactory();

    private WebDriver driver = null;
    @BeforeAll
    public static void init(){

        webDriverFactory.webdravermanagerSetup();
    }
    @BeforeEach
    public void setUp() {
       // ChromeOptions options = new ChromeOptions();
        driver =  webDriverFactory.create();
    }

    @Test
    public void inputRegisrtraionForm(){
        logger.info("Запуск теста: inputRegisrtraionForm");
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-fullscreen");
        mainPage.inputField(USERNAME, name);
        logger.info("Заполнение имени");
        //mainPage.inputName();
        logger.info("Заполнение почты");
        mainPage.inputField(EMAIL,email);
        logger.info("Заполнение пароля");
        mainPage.inputField(PASSWORD , password);
        logger.info("Заполнение подтвержения пароля");
        mainPage.inputField(CONFIRM_PASSWORD, password);
        logger.info("Заполнение даты рождения");
        mainPage.inputField(BIRTHDATE, birthdate);
        logger.info("Заполнение формы уровня языка");
        mainPage.languagelevel();
        logger.info("Нажатие кнопки регистрации");
        mainPage.registrationBtnClick();
        logger.info("Проверка результатов регистрации");
        mainPage.setResultOfRegistration();

    }


    @AfterEach
        public void tearDown () {
             if (driver != null) {
                driver.quit();
            }
        }
}
