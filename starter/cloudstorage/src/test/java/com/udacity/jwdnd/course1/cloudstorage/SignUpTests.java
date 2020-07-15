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
class SignUpTests {

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
	public void getSignUpPage() {
		driver.get("http://localhost:" + this.port + "/signup");
		Assertions.assertEquals("Super Duper Drive - Sign Up", driver.getTitle());
	}

	@Test
	public void signUpPageTests() throws InterruptedException {
		//start the driver, open chrome to our target url
		driver.get("http://localhost:" + this.port + "/signup");

		//test first name error
		WebElement inputField = driver.findElement(By.id("inputFirstName"));
		inputField.sendKeys("a");

		inputField = driver.findElement(By.id("inputLastName"));
		inputField.sendKeys("a");

		inputField = driver.findElement(By.id("inputUsername"));
		inputField.sendKeys("a");

		inputField = driver.findElement(By.id("inputPassword"));
		inputField.sendKeys("a");

		inputField.submit();

		List<WebElement> errorResults = driver.findElements(By.className("my-notify-error"));
		Assertions.assertEquals("First Name should be at least 2 characters.", errorResults.get(0).getText());
		Thread.sleep(1000);

		//test last name error
		inputField = driver.findElement(By.id("inputFirstName"));
		inputField.sendKeys("aa");

		inputField.submit();

		errorResults = driver.findElements(By.className("my-notify-error"));
		System.out.println("errorMessage = " + errorResults.get(0).getText());
		Assertions.assertEquals("Last Name should be at least 2 characters.", errorResults.get(0).getText());
		Thread.sleep(1000);

		//test username error
		inputField = driver.findElement(By.id("inputLastName"));
		inputField.sendKeys("aa");
		inputField.submit();

		errorResults = driver.findElements(By.className("my-notify-error"));
		Assertions.assertEquals("Username should be at least 5 characters.", errorResults.get(0).getText());
		Thread.sleep(1000);

        //test password error
		inputField = driver.findElement(By.id("inputUsername"));
		inputField.sendKeys("aaaaa");
		inputField.submit();

		errorResults = driver.findElements(By.className("my-notify-error"));
		Assertions.assertEquals("Password should be at least 5 characters.", errorResults.get(0).getText());
		Thread.sleep(1000);

		//test username success
		inputField = driver.findElement(By.id("inputPassword"));
		inputField.sendKeys("aaaaa");
		inputField.submit();

		List<WebElement> successResults = driver.findElements(By.className("my-notify-success"));
		Assertions.assertEquals("Welcome, aaa! Please continue to the login page.", successResults.get(0).getText());
		Thread.sleep(1000);

		//test username dup
		inputField = driver.findElement(By.id("inputPassword"));
		inputField.sendKeys("aaaaa");
		inputField.submit();

		errorResults = driver.findElements(By.className("my-notify-error"));
		Assertions.assertEquals("Username is not available - please try again!", errorResults.get(0).getText());
		Thread.sleep(1000);
	}

}
