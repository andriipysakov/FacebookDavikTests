import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.*;

public class FacebookTests {

    private static final String HOME_PAGE_URL = "https://www.facebook.com/";

    private static WebDriver driver;

    @BeforeAll
    public static void classSetup() {
        driver = SharedDriver.getWebDriver();
        driver.get(HOME_PAGE_URL);
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
    public void errorMessageTest() throws InterruptedException {
        driver.findElement(By.xpath("//*[text()='Create new account']")).click();
        assertNotNull(driver.findElement(By.xpath("//*[text()='Sign Up']")));
        Thread.sleep(1000);

        driver.findElement(By.xpath("//*[@name='websubmit']")).click();

        driver.findElement(By.xpath("//*[@name='firstname']")).click();
        WebElement errorFirstname = driver.findElement(By.xpath("//div[@class='_5633 _5634 _53ij' and contains(text(), 'name')]"));
        assertNotNull(errorFirstname);

        driver.findElement(By.xpath("//*[@name='lastname']")).click();
        WebElement errorLastname = driver.findElement(By.xpath("//div[@class='_5633 _5634 _53ij' and contains(text(), 'name')]"));
        assertNotNull(errorLastname);

        driver.findElement(By.xpath("//*[@name='reg_email__']")).click();
        WebElement errorRegEmail = driver.findElement(By.xpath("//div[@class='_5633 _5634 _53ij' and contains(text(), 'reset')]"));
        assertNotNull(errorRegEmail);

        driver.findElement(By.xpath("//*[@name='reg_passwd__']")).click();
        WebElement errorRegPassword = driver.findElement(By.xpath("//div[@class='_5633 _5634 _53ij' and contains(text(), 'combination')]"));
        assertNotNull(errorRegPassword);

        driver.findElement(By.xpath("//*[@name='birthday_month']")).click();
        WebElement errorBirthMonth = driver.findElement(By.xpath("//div[@class='_5633 _5634 _53ij' and contains(text(), 'birthday')]"));
        assertNotNull(errorBirthMonth);

        driver.findElement(By.xpath("//*[@name='birthday_day']")).click();
        WebElement errorBirthDay = driver.findElement(By.xpath("//div[@class='_5633 _5634 _53ij' and contains(text(), 'birthday')]"));
        assertNotNull(errorBirthDay);

        driver.findElement(By.xpath("//*[@name='birthday_year']")).click();
        WebElement errorBirthYear = driver.findElement(By.xpath("//div[@class='_5633 _5634 _53ij' and contains(text(), 'birthday')]"));
        assertNotNull(errorBirthYear);

        driver.findElement(By.xpath("//input[@name='sex' and @value='-1']")).click();
        driver.findElement(By.xpath("//*[@name='websubmit']")).click();
        driver.findElement(By.xpath("//*[@name='preferred_pronoun']")).click();
        WebElement errorPronoun = driver.findElement(By.xpath("//div[@class='_5633 _5634 _53ij' and contains(text(), 'your pronoun')]"));
        assertNotNull(errorPronoun);

        driver.findElement(By.xpath("//input[@name='sex' and @value='-1']")).click();
        driver.findElement(By.xpath("//*[@name='websubmit']")).click();
        driver.findElement(By.xpath("//*[@name='websubmit']")).click();
        driver.findElement(By.xpath("//*[@name='websubmit']")).click();
        driver.findElement(By.xpath("//*[@name='birthday_age']")).click();
        WebElement errorBirthAge = driver.findElement(By.xpath("//div[@class='_5633 _5634 _53ij' and contains(text(), 'age')]"));
        assertNotNull(errorBirthAge);

    }

    @ParameterizedTest
    @CsvSource({"Jan, 1", "Jun, 6", "Dec, 12"})
    public void monthTestParametrized(String monthInput, String monthInputValue) throws InterruptedException {
        driver.findElement(By.xpath("//*[text()='Create new account']")).click();
        assertNotNull(driver.findElement(By.xpath("//*[text()='Sign Up']")));
        Thread.sleep(1000);

        driver.findElement(By.xpath("//*[@title='Month']")).click();
        driver.findElement(By.xpath("//*[text() = '" + monthInput + "']")).click();
        String monthValue = driver.findElement(By.xpath("//*[@title='Month']")).getAttribute("value");

        assertEquals(monthInputValue, monthValue);

    }

    @ParameterizedTest
    @ValueSource(strings = {"1905", "1950", "2020"})
    public void yearTestParametrized(String yearInput) throws InterruptedException {
        driver.findElement(By.xpath("//*[text()='Create new account']")).click();
        assertNotNull(driver.findElement(By.xpath("//*[text()='Sign Up']")));
        Thread.sleep(1000);

        driver.findElement(By.xpath("//*[@title='Year']")).click();
        driver.findElement(By.xpath("//*[text() = '" + yearInput + "']")).click();
        String yearValue = driver.findElement(By.xpath("//*[@title='Year']")).getAttribute("value");

        assertEquals(yearInput, yearValue);

    }

    @Test
    public void radioButtonTest() throws InterruptedException {
        String femaleXPath = "//*[text()='Female']//following-sibling::*[@type='radio']";
        String maleXPath = "//*[text()='Male']//following-sibling::*[@type='radio']";
        String customXPath = "//*[text()='Custom']//following-sibling::*[@type='radio']";

        driver.findElement(By.xpath("//*[text()='Create new account']")).click();
        assertNotNull(driver.findElement(By.xpath("//*[text()='Sign Up']")));
        Thread.sleep(1000);

        WebElement femaleButton = driver.findElement(By.xpath(femaleXPath));
        femaleButton.click();
        String isFemaleChecked = driver.findElement(By.xpath(femaleXPath)).getAttribute("checked");
        assertNotNull(isFemaleChecked);
        assertEquals("true", isFemaleChecked);

        WebElement maleButton = driver.findElement(By.xpath(maleXPath));
        maleButton.click();
        String isMaleChecked = driver.findElement(By.xpath(maleXPath)).getAttribute("checked");
        assertNotNull(isMaleChecked);
        assertEquals("true", isMaleChecked);

        WebElement customButton = driver.findElement(By.xpath(customXPath));
        customButton.click();
        String isCustomChecked = driver.findElement(By.xpath(customXPath)).getAttribute("checked");
        assertNotNull(isCustomChecked);
        assertEquals("true", isCustomChecked);
    }

    @Test
    public void termsLinkTest() throws InterruptedException {
        driver.findElement(By.xpath("//*[text()='Create new account']")).click();
        assertNotNull(driver.findElement(By.xpath("//*[text()='Sign Up']")));
        Thread.sleep(1000);

        driver.findElement(By.xpath("//*[@id='terms-link']")).click();
        for(String str : driver.getWindowHandles()){
            driver.switchTo().window(str);
        }
        Thread.sleep(1000);
        String openTermsPageSource = driver.getCurrentUrl();
        assertNotNull(openTermsPageSource);
        assertEquals("https://www.facebook.com/legal/terms/update", openTermsPageSource);

    }
    @Test
    public void privacyLinkTest() throws InterruptedException {
        driver.findElement(By.xpath("//*[text()='Create new account']")).click();
        assertNotNull(driver.findElement(By.xpath("//*[text()='Sign Up']")));
        Thread.sleep(1000);

        driver.findElement(By.xpath("//*[@id='privacy-link']")).click();
        for(String str : driver.getWindowHandles()){
            driver.switchTo().window(str);
        }
        Thread.sleep(1000);
        String openPrivacyPageSource = driver.getCurrentUrl();
        assertNotNull(openPrivacyPageSource);
        assertEquals("https://www.facebook.com/privacy/policy/?entry_point=data_policy_redirect&entry=0", openPrivacyPageSource);

    }
    @Test
    public void cookieLinkTest() throws InterruptedException {
        driver.findElement(By.xpath("//*[text()='Create new account']")).click();
        assertNotNull(driver.findElement(By.xpath("//*[text()='Sign Up']")));
        Thread.sleep(1000);

        driver.findElement(By.xpath("//*[@id='cookie-use-link']")).click();
        for(String str : driver.getWindowHandles()){
            driver.switchTo().window(str);
        }
        Thread.sleep(1000);
        String openCookiePageSource = driver.getCurrentUrl();
        assertNotNull(openCookiePageSource);
        assertEquals("https://www.facebook.com/privacy/policies/cookies/?entry_point=cookie_policy_redirect&entry=0", openCookiePageSource);

    }
}
