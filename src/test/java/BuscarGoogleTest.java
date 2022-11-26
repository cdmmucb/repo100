import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.testng.Assert.*;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.concurrent.TimeUnit;
import io.github.bonigarcia.wdm.*;



public class BuscarGoogleTest {
    private WebDriver driver;

    @BeforeTest
    public void setDriver() throws Exception{
        String path = "D:\\Downloads\\chromedriver_win32";
        System.setProperty("webdriver.chrome.driver", path);

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test
    public void paginaPrincipalGoogle(){
        //1 prep prueba
        String googleUrl = "https://www.google.com";
        driver.get(googleUrl);

        WebElement campoBusqueda = driver.findElement(By.name("q"));

        campoBusqueda.sendKeys("Universidad Catolica Boliviana");

        campoBusqueda.submit();

        try{
            TimeUnit.SECONDS.sleep(5);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        WebElement resultado = driver.findElement(By.xpath("/html/body/div[7]/div/div[11]/div[2]/div/div/div[2]/div/div[1]/div[2]/div[1]/div/div[2]/h2/span"));

        String label = resultado.getText();
        System.out.println("Texto del resultado:"+label);
        Assert.assertEquals(label,"Universidad Católica Boliviana");
    }

    @AfterTest
    public void closeDriver() throws Exception{
        driver.quit();
    }
}
