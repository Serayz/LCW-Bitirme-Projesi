import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications", "--disable-blink-features=AutomationControlled"); //Removes the detection
        options.setExperimentalOption("useAutomationExtension", false);
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation",}); //Removes the banner

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver(options);


        driver.get("https://www.lcwaikiki.com/");
        driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);

        WebElement cookiePopUp = driver.findElement(By.xpath("//*[@id=\"cookieseal-banner\"]/div/button[2]"));
        if (cookiePopUp.isDisplayed()) {
                 new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(cookiePopUp)).click();
        }

        WebElement girisYap = driver.findElement(By.xpath("//*[@id=\"header__container\"]/header/div[2]/div[3]/div/span/div/a"));
        girisYap.click();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        uyeOlFunction(driver);

        // uye girisi sayfasına dön
        driver.findElement(By.xpath("//*[@id=\"register\"]/div/div/div/form/a")).click();

        uyeGirisi(driver);
        urunSepeteEkleFunction(driver);

    }

    private static void uyeOlFunction(WebDriver driver) {
        WebElement uyeOl = driver.findElement(By.xpath("//*[@id=\"login\"]/div/div/div/div/form/a"));
        uyeOl.click();

        try {
            Thread.sleep(3000);
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


        driver.findElement(By.xpath("//span[text()='E-Posta']")).click();
        driver.findElement(By.xpath("//span[text()='SMS']")).click();

        WebElement checkBox2 = driver.findElement(By.xpath("//*[@id=\"member-privacy-approve-container\"]/div[1]/input"));
        checkBox2.click();

        WebElement uyeOlAgain = driver.findElement(By.xpath("//*[@id=\"register\"]/div/div/div/form/button"));
        uyeOlAgain.click();

        // çarpı
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"react-modal\"]/div/div[2]/div[2]/img"))).click();
    }

    private static void urunSepeteEkleFunction(WebDriver driver) {
        WebElement kategoriKadin = driver.findElement(By.xpath("//*[@id=\"header__container\"]/header/div[3]/nav/ul/li[1]/a"));

        // kadın kategorisinin üzerinde bekleme
        Actions builder = new Actions(driver);
        builder.moveToElement(kategoriKadin).perform();

        // bluz kategorisine tıklama
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"header__container\"]/header/div[3]/nav/ul/li[1]/div/div[1]/div[2]/ul/li[10]/a"))).click();

        // renk arama filtresi
        new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div[5]/div/div[1]/div/div[2]/div/div[2]/div[1]/input"))).sendKeys("s");

        // siyah rengi seçme
        new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div[5]/div/div[1]/div/div[2]/div/div[2]/div[2]/div[3]/span[1]/img"))).click();

        // listedeki ilk urunu secme
        new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div[5]/div/div[2]/div[1]/a/div[1]/img"))).click();

        // sayfadaki mağaza kodu
        String productCode = driver.findElement(By.xpath("//*[@id=\"rightInfoBar\"]/div[1]/div/div[1]/div[1]/div[1]")).getText();

        // M beden urunu seçme
        new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"option-size\"]/a[1]"))).click();

        // sepete ekle butonu
        new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"pd_add_to_cart\"]"))).click();

        // sepete git
        new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"header__container\"]/header/div[1]/div[3]/div/div[2]/a"))).click();

        String productCodeOnBasket = new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"ShoppingCartContent\"]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div/span[2]"))).getText();

        System.out.println(productCode);
        System.out.println(productCodeOnBasket);

        // ürün kodu kontrol
        if (!productCode.contains(productCodeOnBasket)) {
            try {
                throw new Exception("product code not the same");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // ürün beden kontrol
        String productSize = driver.findElement(By.xpath("//*[@id=\"ShoppingCartContent\"]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div/div[2]/span[1]/strong")).getText();
        if (!StringUtils.equals(productSize, "M")) {
            try {
                throw new Exception("product size not the same");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // ürün sayısı kontrol
        String productAmount = driver.findElement(By.xpath("//*[@id=\"ShoppingCartContent\"]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div/div[1]/div/input")).getAttribute("value");
        if (!StringUtils.equals(productAmount, "1")) {
            try {
                throw new Exception("product amount not the same");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.println(productSize + "/" + productAmount);

        // ödeme ekranına git
        driver.findElement(By.xpath("//*[@id=\"ShoppingCartContent\"]/div[1]/div[2]/div[1]/a")).click();

    }


    private static void uyeGirisi(WebDriver driver) {
        WebElement elementEposta = driver.findElement(By.name("email"));
        elementEposta.click();
        elementEposta.sendKeys("testautomationesb@gmail.com");
        WebElement elementSifre = driver.findElement(By.name("password"));
        elementSifre.click();
        elementSifre.sendKeys("Test112.");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement elementGirisYap = driver.findElement(By.xpath("//*[@id=\"login\"]/div/div/div/div/form/button"));
        elementGirisYap.click();

        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

