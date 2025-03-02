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

public class Registration_Form {
    private static final Logger logger = LogManager.getLogger(Registration_Form.class);
    private static WebDriverFactory webDriverFactory = new WebDriverFactory();

    private WebDriver driver = null;
    @BeforeAll
    public static void init(){

        webDriverFactory.webdravermanagerSetup();
    }
    @BeforeEach
    public void setUp() {
    ChromeOptions options = new ChromeOptions();
    driver =  webDriverFactory.create();
    }

    @Test
    public void inputRegisrtraionForm(){
        logger.info("Запуск теста: inputRegisrtraionForm");
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-fullscreen");

        try {
            logger.info("Заполнение имени");
            mainPage.inputName();

            logger.info("Заполнение почты");
            mainPage.inputEmail();
            logger.info("Заполнение пароля");
            mainPage.inputPassword();
            logger.info("Заполнение подтвержения пароля");
            mainPage.inputConfirmPassword();
            logger.info("Заполнение даты рождения");
            mainPage.inputBirthDate();
            logger.info("Заполнение формы уровня языка");
            mainPage.lenguagelevel();
            logger.info("Нажатие кнопки регистрации");
            mainPage.registrationBtnClick();
            logger.info("Проверка результатов регистрации");
            mainPage.setResultOfRegistration();
        }

        catch (Exception e) {
            logger.error("Произошла ошибка: ", e);
            throw e;
        }

    }


    @AfterEach
        public void tearDown () {
             if (driver != null) {
                driver.quit();
            }
        }
}
