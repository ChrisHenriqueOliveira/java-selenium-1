package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import support.Support;
import support.FileName;

import java.util.concurrent.TimeUnit;

public class InformacoesUsuarioTest {
    private WebDriver driver;

    @Rule
    public TestName test = new TestName();

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver","C:\\Users\\Christian\\Desktop\\CursoSelenium\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        driver.manage().window().maximize();
        driver.get("http://www.juliodelima.com.br/taskit/");

        driver.findElement(By.linkText("Sign in")).click();

        WebElement signInBox = driver.findElement(By.id("signinbox"));
        signInBox.findElement(By.name("login")).sendKeys("julio0001");
        signInBox.findElement(By.name("password")).sendKeys("123456");

        driver.findElement(By.linkText("SIGN IN")).click();

        driver.findElement(By.className("me")).click();

        driver.findElement(By.linkText("MORE DATA ABOUT YOU")).click();
    }

    //@Test
    public void testAdicionarUmaInformacaoAdicionalDoUsuario () {
        driver.findElement(By.xpath("//button[@data-target='addmoredata']")).click();

        WebElement addMoreDataBox = driver.findElement(By.id("addmoredata"));

        WebElement cbType = addMoreDataBox.findElement(By.name("type"));
        new Select(cbType).selectByVisibleText("Phone");

        addMoreDataBox.findElement(By.name("contact")).sendKeys("+5511999999999");

        addMoreDataBox.findElement(By.linkText("SAVE")).click();

        String resultMessage = driver.findElement(By.id("toast-container")).getText();
        assertEquals("Your contact has been added!",resultMessage);
    }

    @Test
    public void removeContact() {
        driver.findElement(By.xpath("//span[text()=\"+5511995623017\"]/following-sibling::a")).click();

        driver.switchTo().alert().accept();

        WebElement popupMessage = driver.findElement(By.id("toast-container"));
        String resultMessage = popupMessage.getText();
        assertEquals("Rest in peace, dear phone!",resultMessage);

        String screenshotFile = "C:\\Users\\Christian\\Desktop\\CursoSelenium\\Screenshots\\" + FileName.fileName() + test.getMethodName() + ".png";
        Support.takeScreenshot(driver, screenshotFile);

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.stalenessOf(popupMessage));

        driver.findElement(By.linkText("Logout")).click();
    }

    @After
    public void tearDown() {
        //driver.quit();
    }
}
