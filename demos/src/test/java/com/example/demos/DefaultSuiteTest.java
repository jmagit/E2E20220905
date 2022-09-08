package com.example.demos;

// Generated by Selenium IDE
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.example.selenium.utils.BrowserBot;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.util.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class DefaultSuiteTest {
	private WebDriver driver;
	private Map<String, Object> vars;
	private JavascriptExecutor js;
	private BrowserBot browserBot;

	@BeforeEach
	public void setUp() {
		driver = new ChromeDriver();
		js = (JavascriptExecutor) driver;
		vars = new HashMap<String, Object>();
		browserBot = new BrowserBot(driver);
	}

	@AfterEach
	public void tearDown() {
		driver.quit();
	}

	public void admLogin() {
		logout();
		driver.findElement(By.id("txtUsuario")).sendKeys("adm@kk.kk");
		driver.findElement(By.id("txtPassword")).sendKeys("P@$$w0rd");
		driver.findElement(By.cssSelector(".fa-sign-in-alt")).click();
//		assertThat(driver.findElement(By.id("userData")).getText(), is("Hola Administrador"));
		var result = new WebDriverWait(driver, Duration.ofSeconds(30))
		        .until(ExpectedConditions.textToBe(By.id("userData"), "Hola Administrador"));
		assertTrue( "Sin el saludo", result);
	}

	public void empLogin() {
		logout();
		driver.findElement(By.id("txtUsuario")).sendKeys("emp@kk.kk");
		driver.findElement(By.id("txtPassword")).sendKeys("P@$$w0rd");
		driver.findElement(By.cssSelector(".fa-sign-in-alt")).click();
//		assertThat(driver.findElement(By.id("userData")).getText(), is("Hola Empleado"));
		var result = new WebDriverWait(driver, Duration.ofSeconds(30))
		        .until(ExpectedConditions.textToBe(By.id("userData"), "Hola Empleado"));
		assertTrue(result);
	}
	
//	@Test
//	public void buscar() {
//		driver.get("http://google.es/");
//		Alert alert = new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.alertIsPresent());
//	}

	@Test
	public void navegacion() {
		driver.get("http://192.168.1.156:8181/");
		driver.manage().window().setSize(new Dimension(1212, 667));
		assertThat(driver.getTitle(), is("Entorno de pruebas Web4Testing"));
		driver.findElement(By.linkText("Inicio")).click();
		assertThat(driver.findElement(By.cssSelector(".col-sm > h1")).getText(), is("Entorno de pruebas Web4Testing"));
		driver.findElement(By.linkText("Calculadora")).click();
		assertThat(driver.findElement(By.cssSelector("h1")).getText(), is("Calculadora"));
		assertThat(driver.getTitle(), is("Calculadora"));
		driver.findElement(By.linkText("Compras")).click();
		assertThat(driver.getTitle(), is("Carrito de la compra"));
		driver.findElement(By.linkText("Contactos")).click();
		assertThat(driver.getTitle(), is("Contactos"));
		driver.findElement(By.linkText("Alertas")).click();
		assertThat(driver.findElement(By.cssSelector("h1:nth-child(1)")).getText(), is("Alertas"));
		driver.findElement(By.linkText("Ficheros")).click();
		assertThat(driver.getTitle(), is("Ficheros"));
		driver.findElement(By.linkText("APIs")).click();
		assertThat(driver.getTitle(), is("API REST"));
		driver.findElement(By.cssSelector(".fa-info-circle")).click();
		assertThat(driver.getTitle(), is("Documentación"));
	}

	@Test
	public void alertas() {
		driver.get("http://192.168.1.156:8181/");
		driver.manage().window().setSize(new Dimension(1212, 667));
		driver.findElement(By.linkText("Alertas")).click();
		{
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.textToBe(By.id("crono"), "3 seconds"));
		}
		driver.findElement(By.id("btnAlert")).click();
		assertThat(driver.switchTo().alert().getText(), is("Esta es una alerta"));
		driver.switchTo().alert().accept();
		assertThat(driver.findElement(By.id("CuadroAlerta")).getText(), is("Se a cerrado la alerta\n×"));
		driver.findElement(By.id("btnConfirm")).click();
		assertThat(driver.switchTo().alert().getText(), is("¿Eatas seguro?"));
		driver.switchTo().alert().accept();
		assertThat(driver.findElement(By.id("CuadroAlerta")).getText(), is("Respuesta positiva\n×"));
		driver.findElement(By.id("btnConfirm")).click();
		assertThat(driver.switchTo().alert().getText(), is("¿Eatas seguro?"));
		driver.switchTo().alert().dismiss();
		driver.findElement(By.id("btnPrompt")).click();
		assertThat(driver.switchTo().alert().getText(), is("Dime algo:"));
		{
			Alert alert = driver.switchTo().alert();
			alert.sendKeys("hola");
			alert.accept();
		}
		assertThat(driver.findElement(By.id("CuadroAlerta")).getText(), is("Has dicho: hola\n×"));
		driver.findElement(By.id("btnPrompt")).click();
		assertThat(driver.switchTo().alert().getText(), is("Dime algo:"));
		driver.switchTo().alert().dismiss();
		assertThat(driver.findElement(By.id("CuadroAlerta")).getText(), is("Has cancelado\n×"));
		driver.findElement(By.cssSelector(".btn-outline-success:nth-child(1)")).click();
//		assertThat(driver.findElement(By.id("staticBackdropLabel")).getText(), is("New message"));
//		driver.findElement(By.cssSelector("#staticBackdrop .btn-secondary")).click();
//		driver.findElement(By.cssSelector(".btn-outline-success:nth-child(2)")).click();
//		driver.findElement(By.id("message-text")).click();
//		driver.findElement(By.id("message-text")).sendKeys("Hola");
//		assertThat(driver.findElement(By.id("exampleModalLabel")).getText(), is("New message to @mdo"));
//		driver.findElement(By.cssSelector("#exampleModal span")).click();
//		driver.findElement(By.cssSelector("#staticBackdrop .btn-secondary")).click();
	}

	@Test
	public void cuenta_filas() {
		driver.get("http://192.168.1.156:8181/contactos");
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.titleContains("Contactos"));
		int filas = driver.findElements(By.xpath("//tbody/tr")).size();
		assertEquals(filas, 8);
		driver.findElement(By.cssSelector(".fa-angle-double-right")).click();
		filas = driver.findElements(By.xpath("//tbody/tr")).size();
		assertEquals(filas-1, 2);
	}

	@Test
	public void digitos_calculadora() {
		driver.get("http://192.168.1.156:8181/calculadora");
		driver.manage().window().setSize(new Dimension(1212, 667));
//		driver.findElement(By.linkText("Calculadora")).click();
		driver.findElement(By.cssSelector(".btnDigito:nth-child(8)")).click();
		driver.findElement(By.cssSelector(".btnDigito:nth-child(7)")).click();
		driver.findElement(By.cssSelector(".btnDigito:nth-child(6)")).click();
		driver.findElement(By.cssSelector(".btnDigito:nth-child(12)")).click();
		driver.findElement(By.cssSelector(".btnDigito:nth-child(11)")).click();
		driver.findElement(By.cssSelector(".btnDigito:nth-child(10)")).click();
		driver.findElement(By.cssSelector(".btnDigito:nth-child(16)")).click();
		driver.findElement(By.cssSelector(".btnDigito:nth-child(15)")).click();
		driver.findElement(By.cssSelector(".btnDigito:nth-child(14)")).click();
		driver.findElement(By.cssSelector(".btnDigito:nth-child(19)")).click();
		driver.findElement(By.cssSelector(".btnDigito:nth-child(20)")).click();
		driver.findElement(By.cssSelector(".btnDigito:nth-child(14)")).click();
		driver.findElement(By.cssSelector(".btnDigito:nth-child(18)")).click();
		assertThat(driver.findElement(By.id("txtPantalla")).getText(), is("-9876543210,1"));
		driver.findElement(By.id("btnBorrar")).click();
		driver.findElement(By.id("btnBorrar")).click();
		driver.findElement(By.id("btnBorrar")).click();
		driver.findElement(By.id("btnBorrar")).click();
		assertThat(driver.findElement(By.id("txtPantalla")).getText(), is("-98765432"));
		driver.findElement(By.id("btnBorrar")).click();
		driver.findElement(By.id("btnBorrar")).click();
		driver.findElement(By.id("btnBorrar")).click();
		driver.findElement(By.id("btnBorrar")).click();
		driver.findElement(By.id("btnBorrar")).click();
		driver.findElement(By.id("btnBorrar")).click();
		driver.findElement(By.id("btnBorrar")).click();
		driver.findElement(By.id("btnBorrar")).click();
		assertThat(driver.findElement(By.id("txtPantalla")).getText(), is("0"));
		driver.findElement(By.cssSelector(".btnDigito:nth-child(14)")).click();
		driver.findElement(By.cssSelector(".btnDigito:nth-child(15)")).click();
		driver.findElement(By.cssSelector(".btnDigito:nth-child(16)")).click();
		assertThat(driver.findElement(By.id("txtPantalla")).getText(), is("123"));
		driver.findElement(By.id("btnIniciar")).click();
		assertThat(driver.findElement(By.id("txtPantalla")).getText(), is("0"));
	}

	@Test
	public void digitos_calculadora_por_codigo() {
		driver.get("http://192.168.1.156:8181/calculadora");
		driver.manage().window().setSize(new Dimension(1212, 667));
//		driver.findElement(By.linkText("Calculadora")).click();
		for(var digito=9; digito >= 0; --digito)
			driver.findElement(By.cssSelector("input[value=\'" + digito + "\']")).click();
		driver.findElement(By.cssSelector("input[value=\',\']")).click();
		driver.findElement(By.cssSelector("input[value=\'1\']")).click();
		driver.findElement(By.cssSelector("input[value=\'±\']")).click();
		assertThat(driver.findElement(By.id("txtPantalla")).getText(), is("-9876543210,1"));
		Integer times = 4;
		for (int i = 0; i < times; i++) {
			driver.findElement(By.id("btnBorrar")).click();
		}
		assertThat(driver.findElement(By.id("txtPantalla")).getText(), is("-98765432"));
		times = 8;
		for (int i = 0; i < times; i++) {
			driver.findElement(By.id("btnBorrar")).click();
		}
		assertThat(driver.findElement(By.id("txtPantalla")).getText(), is("0"));
		driver.findElement(By.cssSelector(".btnDigito:nth-child(14)")).click();
		driver.findElement(By.cssSelector(".btnDigito:nth-child(15)")).click();
		driver.findElement(By.cssSelector(".btnDigito:nth-child(16)")).click();
		assertThat(driver.findElement(By.id("txtPantalla")).getText(), is("123"));
		driver.findElement(By.id("btnIniciar")).click();
		assertThat(driver.findElement(By.id("txtPantalla")).getText(), is("0"));
		var secuencia = "1+2+3=";
		for (int i = 0; i < secuencia.length(); i++) {
			driver.findElement(By.cssSelector("input[value=\'" + secuencia.charAt(i) +"\']")).click();
		}
		assertThat(driver.findElement(By.id("txtPantalla")).getText(), is("6"));
//		driver.findElement(By.id("btnIniciar")).click();
//		vars.put("secuencia", js.executeScript("return [\"3\",\"3\",\"-\",\"2\",\"1\",\"=\"]"));
//		collectionSecuencia = (ArrayList) vars.get("secuencia");
//		for (int iBtn = 0; iBtn < collectionSecuencia.size() - 1; iBtn++) {
//			vars.put("btn", collectionSecuencia.get(iBtn));
//			driver.findElement(By.cssSelector("input[value=\'vars.get(\"btn\").toString()\']")).click();
//		}
//		assertThat(driver.findElement(By.id("txtPantalla")).getText(), is("12"));
	}

	@Test
	public void login_admin() {
		logout();
		driver.findElement(By.id("txtUsuario")).sendKeys("adm@kk.kk");
		driver.findElement(By.id("txtPassword")).sendKeys("P@$$w0rd");
		driver.findElement(By.cssSelector(".fa-sign-in-alt")).click();
//		assertThat(driver.findElement(By.id("userData")).getText(), is("Hola Administrador"));
		var result = new WebDriverWait(driver, Duration.ofSeconds(30))
		        .until(ExpectedConditions.textToBe(By.id("userData"), "Hola Administrador"));
		assertTrue(result);
		driver.findElement(By.cssSelector(".fa-sign-out-alt")).click();
	}

	@Test
	public void login_con_fragmentos() {
		admLogin();
		logout();
	}

	@Test
	public void login_incorrecto() {
		driver.get("http://192.168.1.156:8181/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.findElement(By.cssSelector(".fa-sign-in-alt")).click();
		assertThat(driver.switchTo().alert().getText(),
				is("ERRORES:\n   Usuario: Completa este campo\n   Contraseña: Completa este campo"));
		driver.switchTo().alert().accept();
		driver.findElement(By.id("txtUsuario")).sendKeys("admin");
		driver.findElement(By.cssSelector(".fa-sign-in-alt")).click();
		assertThat(driver.switchTo().alert().getText(), is("ERRORES:\n   Contraseña: Completa este campo"));
		driver.switchTo().alert().accept();
		driver.findElement(By.id("txtPassword")).sendKeys("kk");
		driver.findElement(By.cssSelector(".fa-sign-in-alt")).click();
		assertThat(driver.switchTo().alert().getText(),
				is("ERRORES:\n   Contraseña: Utiliza un formato que coincida con el solicitado"));
		driver.switchTo().alert().accept();
		driver.findElement(By.cssSelector(".input-group")).click();
		driver.findElement(By.id("txtPassword")).sendKeys("P@$$w0rs");
		driver.findElement(By.id("txtUsuario")).sendKeys("admin@kk");
		driver.findElement(By.cssSelector(".fa-sign-in-alt")).click();
		Alert alert = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.alertIsPresent());
		assertThat(alert.getText(),
				is("Usuario o contraseña incorrectos."));
		alert.accept();
	}

	@Test
	public void logout() {
		driver.get("http://192.168.1.156:8181/");
		vars.put("usuario", driver.findElement(By.id("userData")).getText());
		if ((Boolean) js.executeScript("return (arguments[0] != \'\')", vars.get("usuario"))) {
			driver.findElement(By.cssSelector(".fa-sign-out-alt")).click();
		}
	}

	@Test
	public void crud() throws IOException {
		File scrFile;
		driver.get("http://192.168.1.156:8181/contactos");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().setSize(new Dimension(1212, 667));
		driver.findElement(By.linkText("Contactos")).click();
		driver.findElement(By.cssSelector(".text-right > .btn")).click();
		driver.findElement(By.id("id")).sendKeys("0");
		driver.findElement(By.id("nombre")).sendKeys("1111");
		driver.findElement(By.id("btnEnviar")).click();
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.id("listado")));
		assertThat(driver.findElement(By.cssSelector("tr:nth-child(2) .container .btn")).getText(), is("Sr. 1111"));
		driver.findElement(By.cssSelector("tr:nth-child(2) .btn-success > .fas")).click();
		driver.findElement(By.id("nombre")).click();
		driver.findElement(By.id("apellidos")).click();
		driver.findElement(By.id("apellidos")).sendKeys("Grillo");
		driver.findElement(By.id("telefono")).sendKeys("555123456");
		driver.findElement(By.id("email")).sendKeys("pepito@grillo");
		driver.findElement(By.id("nacimiento")).sendKeys("01-01-2022");
		driver.findElement(By.id("avatar")).sendKeys("https://randomuser.me/api/portraits/men/44.jpg");
		driver.findElement(By.id("btnEnviar")).click();
//		assertThat(driver.findElement(By.id("alertError")).getText(), is(
//				"ERROR: 400: request.body.telefono should match pattern \"^(\\d{3}\\s){2}\\d{3}$\"\\n×"));
		assertTrue(new WebDriverWait(driver, Duration.ofSeconds(30))
		        .until(ExpectedConditions.textToBe(By.id("alertError"), "ERROR: 400: request.body.telefono should match pattern \"^(\\d{3}\\s){2}\\d{3}$\"\n×")));
		driver.findElement(By.cssSelector("#btnCerrarError > span")).click();
		driver.findElement(By.id("telefono")).clear();
		driver.findElement(By.id("telefono")).sendKeys("555 123 456");
		driver.findElement(By.id("btnEnviar")).click();
        scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("./image1.png"));		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.id("listado")));
		assertThat(driver.findElement(By.cssSelector("tr:nth-child(2) .container .btn")).getText(), is("Sr. 1111 Grillo"));
		driver.findElement(By.cssSelector("tr:nth-child(2) .btn-success")).click();
		driver.findElement(By.id("apellidos")).sendKeys("Grillosss");
		driver.findElement(By.id("btnVolver")).click();
        scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("./image2.png"));		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.id("listado")));
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.id("listado")));
		assertThat(driver.findElement(By.cssSelector("tr:nth-child(2) .container .btn")).getText(), is("Sr. 1111 Grillo"));
		driver.findElement(By.cssSelector("tr:nth-child(2) .btn-info")).click();
		assertThat(driver.findElement(By.cssSelector("h4")).getText(), is("Sr. 1111 Grillo"));
		driver.findElement(By.cssSelector(".btn:nth-child(4)")).click();
		driver.findElement(By.cssSelector("tr:nth-child(2) .far")).click();
		assertThat(driver.switchTo().alert().getText(), is("¿Estas seguro?"));
		driver.switchTo().alert().dismiss();
		assertThat(driver.findElement(By.cssSelector("tr:nth-child(2) .container .btn")).getText(),
				is("Sr. 1111 Grillo"));
		driver.findElement(By.cssSelector("tr:nth-child(2) .far")).click();
		assertThat(driver.switchTo().alert().getText(), is("¿Estas seguro?"));
		driver.switchTo().alert().accept();
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.id("listado")));
		assertThat(driver.findElement(By.cssSelector("tr:nth-child(2) .container .btn")).getText(),
				is("Dr. Adolf Dunster"));
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.id("listado")));
        scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("./image3.png"));		
	}
}
