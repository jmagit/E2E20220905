package com.example.selenium.utils;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BrowserBot {
	private final WebDriver driver;

	public BrowserBot(WebDriver driver) {
		this.driver = driver;
	}

	public void click(By locator) {
		driver.findElement(locator).click();
	}

	public void submit(By locator) {
		driver.findElement(locator).submit();
	}

	public void type(By locator, String text) {
		WebElement element = driver.findElement(locator);
		element.clear();
		element.sendKeys(text);
	}

	public void typeById(String locator, String text) {
		type(By.id(locator), text);
	}
	
	public String getAlertText() {
		return getAlertText(Duration.ofSeconds(30));
	}
	
	public String getAlertText(Duration timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());

		//Store the alert text in a variable
		String text = alert.getText();

		//Press the OK button
		alert.accept();
		return text;
	}
}
