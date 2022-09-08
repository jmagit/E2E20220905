package com.example.demos.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
	private WebDriver driver;
	private final By txtUsuarioBy = By.id("txtUsuario");
	private final By txtPasswordBy = By.id("txtPassword");
	private final By btnSendLoginBy = By.id("btnSendLogin");
	private final By userDataBy = By.id("userData");

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		driver.get("http://192.168.1.156:8181/");
	}

	public LoginPage ponUsuario(String valor) {
		driver.findElement(txtUsuarioBy).sendKeys(valor);
		return this;
	}

	public LoginPage ponPassword(String valor) {
		driver.findElement(txtPasswordBy).sendKeys(valor);
		return this;
	}

	public LoginPage enviarLogin() {
		driver.findElement(btnSendLoginBy).click();
		return this;
	}

	public String textoSaludo() {
		WebElement element = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(userDataBy));
		return element.getText();
	}
}
