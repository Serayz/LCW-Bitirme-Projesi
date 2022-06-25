import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.Console;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications", "--disable-blink-features=AutomationControlled");

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver(
                options
        );

        driver.get("https://www.lcwaikiki.com/");
        driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
        //loginTest(driver);
    }
    private static void loginTest(WebDriver driver) {
        WebElement girisYap = driver.findElement(By.xpath("//*[@id=\"header__container\"]/header/div[2]/div[3]/div/span/div/a"));
        girisYap.click();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement elementEposta = driver.findElement(By.name("email"));
        elementEposta.click();
        elementEposta.sendKeys("hyd@hyd.com");
        WebElement elementSifre = driver.findElement(By.name("password"));
        elementSifre.click();
        elementSifre.sendKeys("951951");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement elementGirisYap = driver.findElement(By.xpath("//*[@id=\"login\"]/div/div/div/div/form/button"));
        elementGirisYap.click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement elementError = driver.findElement(By.xpath("//*[@id=\"login\"]/div/div/div/div/div/p"));
        //elementError.isDisplayed();
        System.out.println(elementError.getText());


    }
}



