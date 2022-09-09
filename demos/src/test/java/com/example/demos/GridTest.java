package com.example.demos;

import static org.junit.jupiter.api.Assertions.*;

import java.net.URL;

import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

class GridTest {
	@ParameterizedTest
	@ValueSource(classes = { org.openqa.selenium.chrome.ChromeOptions.class,
			org.openqa.selenium.firefox.FirefoxOptions.class, org.openqa.selenium.edge.EdgeOptions.class })
	@Execution(ExecutionMode.CONCURRENT)
	void paralellTest(Class<? extends Capabilities> webDriverOptions) throws Exception {
		WebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444"), webDriverOptions.newInstance());
		try {
			driver.get("https://www.selenium.dev/");
			String title = driver.getTitle();
			assertEquals("Selenium", title);
		} finally {
			driver.quit();
		}
	}
}
