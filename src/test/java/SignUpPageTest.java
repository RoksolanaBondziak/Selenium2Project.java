import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SignUpPageTest {
    private static final String HOME_PAGE_URL = "https://www.facebook.com/";
    public static WebDriver driver;
    WebDriverWait wait;
    public final static String createAccountField = "*//a[@ajaxify = '/reg/spotlight/']";
    public final static String firstNameField = "*//input[@name = 'firstname']";
    public final static String lastNameField = "*//input[@name = 'lastname']";
    public final static String emailOrNumberField = "*//input[@name = 'reg_email__']";
    public final static String passwordField = "*//input[@name = 'reg_passwd__']";
    public final static String monthField = "*//select[@title = 'Month']";
    public final static String dayField = "*//select[@name = 'birthday_day']";
    public final static String yearField = "*//select[@name = 'birthday_year']";
    public final static String femaleField = "*//*[text() = 'Female']//following-sibling::*[@value ='1']";
    public final static String maleField = "*//*[text() = 'Male']//following-sibling::*[@value = '2']";
    public final static String customField = "*//*[text() = 'Custom']//following-sibling::*[@value = '-1']";
    public final static String signUpField = "*//button[@name = 'websubmit']";
    public final static String termsField = "*//a[@id = 'terms-link']";
    public final static String dataPolicyField = "*//a[@id = 'privacy-link']";
    public final static String companyField = "//a[text() = 'Company']";
    public final static String productsField = "//a[text() = 'Products']";
    public final static String industriesField = "//a[text() = 'Industries']";
    public final static String knowledgeCenterField = "//a[text() = 'Knowledge Center']";


    @BeforeAll
    public static void classSetup() {
        driver = SharedDriver.getWebDriver();
    }

    @BeforeEach
    public void classSetUpEach() {
        driver.get(HOME_PAGE_URL);
        driver.findElement(By.xpath(createAccountField)).click();
        assertNotNull(driver.findElement(By.xpath("//*[text() = 'Sign Up']")));
        wait = new WebDriverWait(SharedDriver.webDriver, Duration.ofSeconds(5));
    }

    @AfterAll
    public static void classTearDown() {
        SharedDriver.closeDriver();
    }

    @AfterEach
    public void testTeardown() {
        driver.get(HOME_PAGE_URL);
    }

    @Test
    public void FirstNameErrorMessageTest() {

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(firstNameField))).click();
        driver.findElement(By.xpath(firstNameField)).click();
        WebElement firstNameError = driver.findElement(By.xpath("//*[contains(text(),'your name')]"));
        assertNotNull(firstNameError);
    }

    @Test
    public void LastNameErrorMessageTest() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(lastNameField))).click();
        driver.findElement(By.xpath(lastNameField)).click();
        WebElement lastNameError = driver.findElement(By.xpath("//*[contains(text(),'your name')]"));
        assertNotNull(lastNameError);
    }

    @Test
    public void emailOrPasswordErrorMessageTest() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(emailOrNumberField))).click();
        driver.findElement(By.xpath(emailOrNumberField)).click();
        WebElement emailOrNumberError = driver.findElement(By.xpath("//*[contains(text(),'reset your')]"));
        assertNotNull(emailOrNumberError);
    }

    @Test
    public void passwordErrorMessageTest() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(passwordField))).click();
        driver.findElement(By.xpath(passwordField)).click();
        WebElement passwordError = driver.findElement(By.xpath("//*[contains(text(),'six numbers')]"));
        assertNotNull(passwordError);
    }

    @Test
    public void monthErrorMessageTest() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(monthField))).click();
        driver.findElement(By.xpath(monthField)).click();
        WebElement monthError = driver.findElement(By.xpath("//*[contains(text(),'real birthday')]"));
        assertNotNull(monthError);
    }

    @Test
    public void dayErrorMessageTest() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(dayField))).click();
        driver.findElement(By.xpath(dayField)).click();
        WebElement dayError = driver.findElement(By.xpath("//*[contains(text(),'real birthday')]"));
        assertNotNull(dayError);
    }

    @Test
    public void yearErrorMessageTest() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(yearField))).click();
        driver.findElement(By.xpath(yearField)).click();
        WebElement yearError = driver.findElement(By.xpath("//*[contains(text(),'real birthday')]"));
        assertNotNull(yearError);
    }

    static Stream<Arguments> dataProvider() {

        return Stream.of(
                Arguments.of("1", "Jan"),
                Arguments.of("2", "Feb"),
                Arguments.of("3", "Mar"),
                Arguments.of("4", "Apr"),
                Arguments.of("5", "May"),
                Arguments.of("6", "Jun"),
                Arguments.of("7", "Jul"),
                Arguments.of("8", "Aug"),
                Arguments.of("9", "Sep"),
                Arguments.of("10", "Oct"),
                Arguments.of("11", "Nov"),
                Arguments.of("12", "Dec")
        );
    }

    @ParameterizedTest
    @MethodSource("dataProvider")
    public void monthParametrizedTest(String monthValue, String monthInput) {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(monthField))).click();
        driver.findElement(By.xpath("//*[@value ='" + monthValue + "' and text() = '" + monthInput + "']")).click();
        String monthNewValue = driver.findElement(By.xpath(monthField)).getAttribute("value");
        assertEquals(monthValue, monthNewValue);
    }

    @ParameterizedTest
    @ValueSource(strings = {"1905", "1950", "2020"})
    public void yearParametrizedTest(String yearInput) throws InterruptedException {

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(yearField))).click();
        driver.findElement(By.xpath("//*[text() = '" + yearInput + "']")).click();
        String yearValue = driver.findElement(By.xpath(yearField)).getAttribute("value");
        assertEquals(yearInput, yearValue);
    }

    @Test
    public void radioButtonTest() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(femaleField)));
        WebElement femaleValue = driver.findElement(By.xpath(femaleField));
        femaleValue.click();
        String femaleInput = driver.findElement(By.xpath(femaleField)).getAttribute("checked");
        assertNotNull(femaleInput);
        assertEquals("true", femaleInput);

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(maleField)));
        WebElement maleValue = driver.findElement(By.xpath(maleField));
        maleValue.click();
        String maleInput = driver.findElement(By.xpath(maleField)).getAttribute("checked");
        assertNotNull(maleInput);
        assertEquals("true", maleInput);

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(customField))).click();
        WebElement customValue = driver.findElement(By.xpath(customField));
        customValue.click();
        String customInput = driver.findElement(By.xpath(customField)).getAttribute("checked");
        assertNotNull(customInput);
        assertEquals("true", customInput);

    }

    @Test
    public void termsTest() throws InterruptedException {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(termsField))).click();
        assertNotNull(termsField);
        for (String str : driver.getWindowHandles()) {
            driver.switchTo().window(str);
        }
        String newURL = driver.getCurrentUrl();
        assertEquals("https://www.facebook.com/legal/terms/update", newURL);

    }

    @Test
    public void dataPolicyTest() {

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(dataPolicyField))).click();
        driver.findElement(By.xpath(dataPolicyField)).click();
        assertNotNull(dataPolicyField);
        for (String str : driver.getWindowHandles()) {
            driver.switchTo().window(str);
        }
        String newURL = driver.getCurrentUrl();
        assertEquals("https://www.facebook.com/about/privacy/update", newURL);
    }

    @Test
    public void davikSite() {
        driver.navigate().to("https://daviktapes.com");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(companyField)));
        WebElement element1 = driver.findElement(By.xpath(companyField));
        Actions companyActions = new Actions(driver);
        companyActions.moveToElement(element1).build().perform();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(productsField)));
        WebElement element2 = driver.findElement(By.xpath(productsField));
        Actions productsActions = new Actions(driver);
        productsActions.moveToElement(element2).build().perform();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(industriesField)));
        WebElement element3 = driver.findElement(By.xpath(industriesField));
        Actions industriesActions = new Actions(driver);
        industriesActions.moveToElement(element3).build().perform();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(knowledgeCenterField)));
        WebElement element4 = driver.findElement(By.xpath(knowledgeCenterField));
        Actions knowledgeCenterActions = new Actions(driver);
        knowledgeCenterActions.moveToElement(element4).build().perform();
    }
}
