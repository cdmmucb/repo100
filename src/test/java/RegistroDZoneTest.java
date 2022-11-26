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

public class RegistroDZoneTest {

    private WebDriver driver;

    @BeforeTest
    public void setDriver() throws Exception{
        String path = "D:\\Downloads\\chromedriver_win32";
        System.setProperty("webdriver.chrome.driver", path);

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test
    public void verificarMensajeErrorAlRegistrar(){
      //prep
        String dzoneUrl="https://dzone.com";
        driver.get(dzoneUrl);
        //logica
        WebElement joinLink = driver.findElement(By.xpath("//*[@id=\"ng-app\"]/body/div[2]/div/div/div[1]/div/div[2]/div[2]/a[2]"));
        String linkText = joinLink.getText();
        System.out.println("Tex de link: "+linkText);

        joinLink.click();
        try{
            TimeUnit.SECONDS.sleep(3);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        WebElement joinButton = driver.findElement(By.xpath("/html/body/div[7]/div[2]/div[2]/users-registration/div/div/div[2]/div[3]/button"));
        joinButton.click();
        try{
            TimeUnit.SECONDS.sleep(3);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        // verificacion o assert
        /*WebElement iconAlert = driver.findElement(By.xpath("//*[@id=\\\"login\\\"]/div/div[2]/div[1]/div[2]/form/div[1]/span[2]/i"));
        Assert.assertEquals(true,iconAlert.isDisplayed());*/

        WebElement emailErrorMensaje = driver.findElement(By.xpath("/html/body/div[7]/div[2]/div[2]/users-registration/div/div/div[2]/div[1]/div[2]/form/div[1]"));
        String attribute =emailErrorMensaje.getAttribute("data-validate");
        System.out.println("valor del atributo: "+attribute);
        Assert.assertEquals("Please enter a valid email address",attribute);

    }

    @AfterTest
    public void closeDriver() throws Exception{
        driver.quit();
    }
}
