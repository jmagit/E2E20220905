package com.example.demos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;

public class AccesoStepDefinitions {
	private WebDriver driver;
	private final By txtUsuarioBy = By.id("txtUsuario");
	private final By txtPasswordBy = By.id("txtPassword");
	private final By btnSendLoginBy = By.id("btnSendLogin");
	private final By userDataBy = By.id("userData");

	@Before("@browser")
	public void openBrowser() {
		driver = new ChromeDriver();
	}

	@After("@browser")
	public void closeBrowser() {
		driver.quit();
	}

	@Dado("que estoy en la página principal")
	public void que_estoy_en_la_aplicacion() {
		driver.get("http://192.168.1.156:8181/");
	}

	@Cuando("pongo {string} como usuario")
	public void pon_usuario(String valor) {
		driver.findElement(txtUsuarioBy).sendKeys(valor);
	}

	@Cuando("pongo {string} como contraseña")
	public void pon_password(String valor) {
		driver.findElement(txtPasswordBy).sendKeys(valor);
	}

	@Entonces("me saluda con {string}")
	public void me_saluda_con(String valor) {
		driver.findElement(btnSendLoginBy).click();
		WebElement element = new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.visibilityOfElementLocated(userDataBy));
		assertEquals(valor, element.getText());
	}
}
