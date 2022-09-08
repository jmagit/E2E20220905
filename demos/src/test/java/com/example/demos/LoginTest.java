package com.example.demos;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.example.demos.pages.LoginPage;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class LoginTest {
	private WebDriver driver;

	@BeforeEach
	public void setUp() throws MalformedURLException {
		//driver = new ChromeDriver();
		FirefoxOptions firefoxOptions = new FirefoxOptions();
		driver = new RemoteWebDriver(new URL("http://localhost:4449"), firefoxOptions);

	}

	@AfterEach
	public void tearDown() {
		driver.quit();
	}

	@Test
	public void login_como_administrador_Test() {
		LoginPage page = new LoginPage(driver);
		page.ponUsuario("adm@kk.kk");
		page.ponPassword("P@$$w0rd");
		page.enviarLogin();
		assertEquals("Hola Administrador", page.textoSaludo());
	}

	@Test
	public void login_como_empleado_Test() {
		LoginPage page = new LoginPage(driver);
		assertEquals("Hola Empleado", page.ponUsuario("emp@kk.kk").ponPassword("P@$$w0rd").enviarLogin().textoSaludo());
	}

}
