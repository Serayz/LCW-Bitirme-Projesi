import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.builder.ToStringExclude;
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

        WebElement girisYap = driver.findElement(By.xpath("//*[@id=\"header__container\"]/header/div[2]/div[3]/div/span/div/a"));
        girisYap.click();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement uyeOl = driver.findElement(By.xpath("//*[@id=\"login\"]/div/div/div/div/form/a"));
        uyeOl.click();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement elementEmail = driver.findElement(By.xpath("//*[@id=\"register\"]/div/div/div/form/div[1]/input"));
        elementEmail.click();
        elementEmail.sendKeys("hyd@hyd.com");

        WebElement elementPassword = driver.findElement(By.xpath("//*[@id=\"register\"]/div/div/div/form/div[2]/div/input"));
        elementPassword.click();
        elementPassword.sendKeys("ABC_DEF*123");

        WebElement phone = driver.findElement(By.xpath("//*[@id=\"register\"]/div/div/div/form/div[3]/input"));
        phone.click();
        phone.sendKeys("019121180");

        WebElement checkBox1 = driver.findElement(By.xpath("//*[@id=\"register\"]/div/div/div/form/div[5]/label[1]/span"));
        checkBox1.click();

        WebElement checkBox2 = driver.findElement(By.xpath("//*[@id=\"member-privacy-approve-container\"]/div[1]/input"));
        checkBox2.click();

    }

    private static void loginTest(WebDriver driver) {
        WebElement girisYap = driver.findElement(By.xpath("//*[@id=\"header__container\"]/header/div[2]/div[3]/div/span/div/a"));
        girisYap.click();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement elementEmail = driver.findElement(By.name("email"));
        elementEmail.click();
        elementEmail.sendKeys("testautomationesb@gmail.com");
        WebElement elementPassword = driver.findElement(By.name("password"));
        elementPassword.click();
        elementPassword.sendKeys("Auto5keuro.");

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



