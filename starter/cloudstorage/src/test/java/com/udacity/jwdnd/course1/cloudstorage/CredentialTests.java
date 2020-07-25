package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CredentialTests {

    @LocalServerPort
    private int port;

    private WebDriver driver;

    @BeforeAll
    static void beforeAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void beforeEach() {
        this.driver = new ChromeDriver();
    }

    @AfterEach
    public void afterEach() {
        if (this.driver != null) {
            driver.quit();
        }
    }

    @Test
    public void credentialHappyPathTests() throws InterruptedException {
        //start the driver, open chrome to our target url
        driver.get("http://localhost:" + this.port + "/signup");

        //create a user for testing login
        WebElement inputField = driver.findElement(By.id("inputFirstName"));
        inputField.sendKeys("aa");

        inputField = driver.findElement(By.id("inputLastName"));
        inputField.sendKeys("aa");

        inputField = driver.findElement(By.id("inputUsername"));
        inputField.sendKeys("aaaaa");

        inputField = driver.findElement(By.id("inputPassword"));
        inputField.sendKeys("aaaaa");

        inputField.submit();
        Thread.sleep(1000);

        //navigate to login page
        driver.get("http://localhost:" + this.port + "/login");

        //login success
        inputField = driver.findElement(By.id("inputUsername"));
        inputField.sendKeys("aaaaa");

        inputField = driver.findElement(By.id("inputPassword"));
        inputField.sendKeys("aaaaa");

        inputField.submit();
        Thread.sleep(1000);
        Assertions.assertEquals("Super Duper Drive - Home", driver.getTitle());

        //open credentials section
        inputField = driver.findElement(By.id("credSection"));
        inputField.click();

        //add credentials
        addCredentials("1", "http://wwww.test.com", "testing", "test123");
        addCredentials("2", "http://www.testagain.com", "test2", "test123123");
        addCredentials("3", "http://www.test3.com", "test3", "test333");

        //edit credentials
        editCredentials("credEdit1", "http://wwww.test.comaaa", "testingaaa", "test123aaa");
        editCredentials("credEdit2", "http://www.testagain.comaaa", "test2aaa", "test123123aaa");
        editCredentials("credEdit3", "http://www.test3.comaaa", "test3aaa", "test333aaa");

        //delete credentials
        deleteCredentials("credDelete1");
        deleteCredentials("credDelete2");
        deleteCredentials("credDelete3");


    }

    public void addCredentials(String id, String url, String username, String password ) throws InterruptedException{
        WebElement inputField = driver.findElement(By.linkText("+ Add a New Credential"));
        inputField.click();
        Thread.sleep(1000);

        inputField = driver.findElement(By.id("credential-url"));
        inputField.sendKeys(url);

        inputField = driver.findElement(By.id("credential-username"));
        inputField.sendKeys(username);

        inputField = driver.findElement(By.id("credential-password"));
        inputField.sendKeys(password);

        inputField.submit();
        Thread.sleep(1000);

        List<WebElement> successResults = driver.findElements(By.className("my-notify-success"));
        Assertions.assertEquals("The Add action was successful.", successResults.get(0).getText());

        verifyViewValues(id, url, username, password);
    }

    public void deleteCredentials(String className) throws InterruptedException{
        WebElement inputField = driver.findElement(By.className(className));
        inputField.click();
        Thread.sleep(1000);

        List<WebElement> successResults = driver.findElements(By.className("my-notify-success"));
        Assertions.assertEquals("The Delete action was successful.", successResults.get(0).getText());
    }

    public void editCredentials(String className, String url, String username, String password) throws InterruptedException {
        String id = className.substring(className.length() - 1);

        WebElement inputField = driver.findElement(By.className(className));
        inputField.click();
        Thread.sleep(1000);

        inputField = driver.findElement(By.id("credential-url"));
        inputField.clear();
        inputField.sendKeys(url);

        inputField = driver.findElement(By.id("credential-username"));
        inputField.clear();
        inputField.sendKeys(username);

        inputField = driver.findElement(By.id("credential-password"));
        inputField.clear();
        inputField.sendKeys(password);

        inputField.submit();
        Thread.sleep(2000);

        List<WebElement> successResults = driver.findElements(By.className("my-notify-success"));
        Assertions.assertEquals("The Edit action was successful.", successResults.get(0).getText());

        verifyViewValues(id, url, username, password);
    }

    public void verifyViewValues(String id, String url, String username, String password){
        List<WebElement> viewValue = driver.findElements(By.className("credViewUrl" + id));
        Assertions.assertEquals(url, viewValue.get(0).getText());
        viewValue = driver.findElements(By.className("credViewUsername" + id));
        Assertions.assertEquals(username, viewValue.get(0).getText());
        viewValue = driver.findElements(By.className("credViewPassword" + id));
        Assertions.assertEquals(password, viewValue.get(0).getText());
    }

}
