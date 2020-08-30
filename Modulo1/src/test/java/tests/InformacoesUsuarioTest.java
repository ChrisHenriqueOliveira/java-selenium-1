package tests;

import static org.junit.Assert.*;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class InformacoesUsuarioTest {

    @Test
    public void testAdicionarUmaInformacaoAdicionalDoUsuario () {
        System.setProperty("webdriver.chrome.driver","C:\\Users\\Christian\\Desktop\\CursoSelenium\\drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        driver.manage().window().maximize();
        driver.get("http://www.juliodelima.com.br/taskit/");

        driver.findElement(By.linkText("Sign in")).click();

        WebElement signInBox = driver.findElement(By.id("signinbox"));
        signInBox.findElement(By.name("login")).sendKeys("julio0001");
        signInBox.findElement(By.name("password")).sendKeys("123456");

        driver.findElement(By.linkText("SIGN IN")).click();

        String nameLogged = driver.findElement(By.className("me")).getText();

        assertEquals("Hi, Julio", nameLogged);
    }
}
