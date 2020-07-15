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
class LoginTests {

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
	public void getLoginPage() {
		driver.get("http://localhost:" + this.port + "/login");
		Assertions.assertEquals("Super Duper Drive - Login", driver.getTitle());
	}

	@Test
	public void loginPageTests() throws InterruptedException {
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

		//login error
		inputField = driver.findElement(By.id("inputUsername"));
		inputField.sendKeys("a");

		inputField = driver.findElement(By.id("inputPassword"));
		inputField.sendKeys("a");

		inputField.submit();
		Thread.sleep(1000);

		//login success
		inputField = driver.findElement(By.id("inputUsername"));
		inputField.sendKeys("aaaaa");

		inputField = driver.findElement(By.id("inputPassword"));
		inputField.sendKeys("aaaaa");

		inputField.submit();
		Thread.sleep(1000);

		//make sure home page is accessible
		driver.get("http://localhost:" + this.port + "/home");
		Assertions.assertEquals("Super Duper Drive - Home", driver.getTitle());
	}

}
