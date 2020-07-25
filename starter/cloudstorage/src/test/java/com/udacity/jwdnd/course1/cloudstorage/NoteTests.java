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
class NoteTests {

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
    public void noteHappyPathTests() throws InterruptedException {
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

        //open notes section
        inputField = driver.findElement(By.id("noteSection"));
        inputField.click();

        //add notes
        addNotes("1", "Title of Notes", "Description goes here");
        addNotes("2", "Note2", "Lorem ipsum and all that jazz....");
        addNotes("3", "Note3", "Three is a wonderful number!");

        //edit notes
        editNotes("noteEdit1", "Subtitle of Notes", "Description goes here!!!!!!");
        editNotes("noteEdit2", "Note 2", "Lorem ipsum is wonderful!");
        editNotes("noteEdit3", "Note 3", "This is a test!");

        //delete notes
        deleteNotes("noteDelete1");
        deleteNotes("noteDelete2");
        deleteNotes("noteDelete3");


    }

    public void addNotes(String id, String title, String description) throws InterruptedException{
        WebElement inputField = driver.findElement(By.linkText("+ Add a New Note"));
        inputField.click();
        Thread.sleep(1000);

        inputField = driver.findElement(By.id("note-title"));
        inputField.sendKeys(title);

        inputField = driver.findElement(By.id("note-description"));
        inputField.sendKeys(description);

        inputField.submit();
        Thread.sleep(1000);

        List<WebElement> successResults = driver.findElements(By.className("note-notify-success"));
        Assertions.assertEquals("The Add action was successful.", successResults.get(0).getText());

        verifyViewValues(id, title, description);
    }

    public void deleteNotes(String className) throws InterruptedException{
        WebElement inputField = driver.findElement(By.className(className));
        inputField.click();
        Thread.sleep(1000);

        List<WebElement> successResults = driver.findElements(By.className("note-notify-success"));
        Assertions.assertEquals("The Delete action was successful.", successResults.get(0).getText());
        Assertions.assertThrows(Exception.class, () ->  inputField.click());
    }

    public void editNotes(String className, String title, String description) throws InterruptedException {
        String id = className.substring(className.length() - 1);

        WebElement inputField = driver.findElement(By.className(className));
        inputField.click();
        Thread.sleep(1000);

        inputField = driver.findElement(By.id("note-title"));
        inputField.clear();
        inputField.sendKeys(title);

        inputField = driver.findElement(By.id("note-description"));
        inputField.clear();
        inputField.sendKeys(description);

        inputField.submit();
        Thread.sleep(2000);

        List<WebElement> successResults = driver.findElements(By.className("note-notify-success"));
        Assertions.assertEquals("The Edit action was successful.", successResults.get(0).getText());

        verifyViewValues(id, title, description);
    }

    public void verifyViewValues(String id, String title, String description){
        List<WebElement> viewValue = driver.findElements(By.className("noteViewTitle" + id));
        Assertions.assertEquals(title, viewValue.get(0).getText());
        viewValue = driver.findElements(By.className("noteViewDescription" + id));
        Assertions.assertEquals(description, viewValue.get(0).getText());
    }

}
