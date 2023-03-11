import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DavikTests {


    private static WebDriver driver;

    @BeforeAll
    public static void classSetup() {
        driver = SharedDriver.getWebDriver();
        driver.get("https://www.daviktapes.com/");
    }

    @AfterAll
    public static void classTearDown() {
        SharedDriver.closeDriver();

    }

    @AfterEach
    public void testTeardown() {

        driver.get("https://www.daviktapes.com/");
    }


    @Test
    public void actionTopMenuTest(){
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='Company']")));

        WebElement elementCompany = driver.findElement(By.xpath("//a[text()='Company']"));
        Actions actionsCompany = new Actions(driver);
        actionsCompany.moveToElement(elementCompany).build().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='About us']")));

        WebElement elementProducts = driver.findElement(By.xpath("//*[text()='Products']"));
        Actions actionsProducts = new Actions(driver);
        actionsProducts.moveToElement(elementProducts).build().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Carry Handle Tape']")));

        WebElement elementIndustries = driver.findElement(By.xpath("//*[text()='Industries']"));
        Actions actionsIndustries = new Actions(driver);
        actionsIndustries.moveToElement(elementIndustries).build().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Agriculture']")));

        WebElement elementKnowledge = driver.findElement(By.xpath("//*[text()='Knowledge Center']"));
        Actions actionsKnowledge = new Actions(driver);
        actionsKnowledge.moveToElement(elementKnowledge).build().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Articles']")));


    }

    @Test
    public void waitAndClickTest() {
//        pause();
        WebDriverWait wait = new WebDriverWait(driver, 5);
//        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='Company']"))).click();
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Company']"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Company']"))).click();
    }


}
