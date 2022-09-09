package com.example.demos;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.WebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

class MultiBrowsersTest {
	WebDriver driver;

	@AfterEach
	void teardown() {
		driver.quit();
	}

	@ParameterizedTest
	@ValueSource(classes = { org.openqa.selenium.chrome.ChromeDriver.class,
			org.openqa.selenium.firefox.FirefoxDriver.class })
	void test(Class<? extends WebDriver> webDriverClass) {
		// Driver management and WebDriver instantiation
		driver = WebDriverManager.getInstance(webDriverClass).create();

		driver.get("https://bonigarcia.dev/selenium-webdriver-java/");
		String title = driver.getTitle();
		assertEquals("Hands-On Selenium WebDriver with Java", title);
	}
}
