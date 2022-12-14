package com.example.demos;

import static org.junit.Assert.assertThat;
// Generated by Selenium IDE
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import java.util.*;
import java.net.MalformedURLException;
import java.net.URL;

public class NavegacionTest {
  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;
  
  @BeforeEach
  public void setUp() {
    //driver = new ChromeDriver();
    driver = new EdgeDriver();
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
  }
  @AfterEach
  public void tearDown() {
    driver.quit();
  }
  
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
    assertThat(driver.getTitle(), is("Documentaci??n"));
  }
}
