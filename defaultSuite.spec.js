// Generated by Selenium IDE
const { Builder, By, Key, until } = require('selenium-webdriver')
const assert = require('assert')

describe('Default Suite', function() {
  this.timeout(30000)
  let driver
  let vars
  beforeEach(async function() {
    driver = await new Builder().forBrowser('chrome').build()
    vars = {}
  })
  afterEach(async function() {
    await driver.quit();
  })
  async function logout() {
    await driver.get("http://192.168.1.156:8181/")
    vars["usuario"] = await driver.findElement(By.id("userData")).getText()
    if (!!await driver.executeScript("return (arguments[0] != \'\')", vars["usuario"])) {
      await driver.findElement(By.css(".fa-sign-out-alt")).click()
    }
  }
  async function admlogin() {
    logout()
    await driver.findElement(By.id("txtUsuario")).sendKeys("adm@kk.kk")
    await driver.findElement(By.id("txtPassword")).sendKeys("P@$$w0rd")
    await driver.findElement(By.css(".fa-sign-in-alt")).click()
    assert(await driver.findElement(By.id("userData")).getText() == "Hola Administrador")
  }
  it('navegacion', async function() {
    await driver.get("http://192.168.1.156:8181/")
    await driver.manage().window().setRect({ width: 1212, height: 667 })
    assert(await driver.getTitle() == "Entorno de pruebas Web4Testing")
    await driver.findElement(By.linkText("Inicio")).click()
    assert(await driver.findElement(By.css(".col-sm > h1")).getText() == "Entorno de pruebas Web4Testing")
    await driver.findElement(By.linkText("Calculadora")).click()
    assert(await driver.findElement(By.css("h1")).getText() == "Calculadora")
    assert(await driver.getTitle() == "Calculadora")
    await driver.findElement(By.linkText("Compras")).click()
    assert(await driver.getTitle() == "Carrito de la compra")
    await driver.findElement(By.linkText("Contactos")).click()
    assert(await driver.getTitle() == "Contactos")
    await driver.findElement(By.linkText("Alertas")).click()
    assert(await driver.findElement(By.css("h1:nth-child(1)")).getText() == "Alertas")
    await driver.findElement(By.linkText("Ficheros")).click()
    assert(await driver.getTitle() == "Ficheros")
    await driver.findElement(By.linkText("APIs")).click()
    assert(await driver.getTitle() == "API REST")
    await driver.findElement(By.css(".fa-info-circle")).click()
    assert(await driver.getTitle() == "Documentación")
  })
  it('alertas', async function() {
    await driver.get("http://192.168.1.156:8181/")
    await driver.manage().window().setRect({ width: 1212, height: 667 })
    await driver.findElement(By.linkText("Alertas")).click()
    await driver.wait(until.elementTextIs(await driver.findElement(By.id("crono")), '3 seconds'), 30000)
    await driver.findElement(By.id("btnAlert")).click()
    assert(await driver.switchTo().alert().getText() == "Esta es una alerta")
    assert(await driver.findElement(By.id("CuadroAlerta")).getText() == "Se a cerrado la alerta\\\\n×")
    await driver.findElement(By.id("btnConfirm")).click()
    assert(await driver.switchTo().alert().getText() == "¿Eatas seguro?")
    await driver.switchTo().alert().accept()
    assert(await driver.findElement(By.id("CuadroAlerta")).getText() == "Respuesta positiva\\\\n×")
    await driver.findElement(By.id("btnConfirm")).click()
    assert(await driver.switchTo().alert().getText() == "¿Eatas seguro?")
    await driver.switchTo().alert().dismiss()
    await driver.findElement(By.id("btnPrompt")).click()
    assert(await driver.switchTo().alert().getText() == "Dime algo:")
    {
      const alert = await driver.switchTo().alert()
      await alert.sendKeys("hola")
      await alert.accept()
    }
    assert(await driver.findElement(By.id("CuadroAlerta")).getText() == "Has dicho: hola\\\\n×")
    await driver.findElement(By.id("btnPrompt")).click()
    assert(await driver.switchTo().alert().getText() == "Dime algo:")
    await driver.switchTo().alert().dismiss()
    assert(await driver.findElement(By.id("CuadroAlerta")).getText() == "Has cancelado\\\\n×")
    await driver.findElement(By.css(".btn-outline-success:nth-child(1)")).click()
    assert(await driver.findElement(By.id("staticBackdropLabel")).getText() == "New message")
    await driver.findElement(By.css("#staticBackdrop .btn-secondary")).click()
    await driver.findElement(By.css(".btn-outline-success:nth-child(2)")).click()
    await driver.findElement(By.id("message-text")).click()
    await driver.findElement(By.id("message-text")).sendKeys("Hola")
    assert(await driver.findElement(By.id("exampleModalLabel")).getText() == "New message to @mdo")
    await driver.findElement(By.css("#exampleModal span")).click()
    await driver.findElement(By.css("#staticBackdrop .btn-secondary")).click()
  })
  it('adm login', async function() {
    logout()
    await driver.findElement(By.id("txtUsuario")).sendKeys("adm@kk.kk")
    await driver.findElement(By.id("txtPassword")).sendKeys("P@$$w0rd")
    await driver.findElement(By.css(".fa-sign-in-alt")).click()
    assert(await driver.findElement(By.id("userData")).getText() == "Hola Administrador")
  })
  it('cuenta filas', async function() {
    await driver.get("http://192.168.1.156:8181/contactos")
    vars["filas"] = await driver.findElements(By.xpath("//tbody/tr")).length
    assert(vars["filas"].toString() == "8")
    console.log("Total filas: vars["filas"]")
    await driver.findElement(By.css(".fa-angle-double-right")).click()
    vars["filas"] = await driver.findElements(By.xpath("//tbody/tr")).length
    console.log("Total filas: vars["filas"]")
    vars["filas"] = await driver.executeScript("return arguments[0]-1", vars["filas"])
    console.log("Ultima filas: vars["filas"]")
    assert(vars["filas"].toString() == "2")
  })
  it('dígitos calculadora', async function() {
    await driver.get("http://192.168.1.156:8181/")
    await driver.manage().window().setRect({ width: 1212, height: 667 })
    await driver.findElement(By.linkText("Calculadora")).click()
    await driver.findElement(By.css(".btnDigito:nth-child(8)")).click()
    await driver.findElement(By.css(".btnDigito:nth-child(7)")).click()
    await driver.findElement(By.css(".btnDigito:nth-child(6)")).click()
    await driver.findElement(By.css(".btnDigito:nth-child(12)")).click()
    await driver.findElement(By.css(".btnDigito:nth-child(11)")).click()
    await driver.findElement(By.css(".btnDigito:nth-child(10)")).click()
    await driver.findElement(By.css(".btnDigito:nth-child(16)")).click()
    await driver.findElement(By.css(".btnDigito:nth-child(15)")).click()
    await driver.findElement(By.css(".btnDigito:nth-child(14)")).click()
    await driver.findElement(By.css(".btnDigito:nth-child(19)")).click()
    await driver.findElement(By.css(".btnDigito:nth-child(20)")).click()
    await driver.findElement(By.css(".btnDigito:nth-child(14)")).click()
    await driver.findElement(By.css(".btnDigito:nth-child(18)")).click()
    assert(await driver.findElement(By.id("txtPantalla")).getText() == "-9876543210,1")
    await driver.findElement(By.id("btnBorrar")).click()
    await driver.findElement(By.id("btnBorrar")).click()
    await driver.findElement(By.id("btnBorrar")).click()
    await driver.findElement(By.id("btnBorrar")).click()
    assert(await driver.findElement(By.id("txtPantalla")).getText() == "-98765432")
    await driver.findElement(By.id("btnBorrar")).click()
    await driver.findElement(By.id("btnBorrar")).click()
    await driver.findElement(By.id("btnBorrar")).click()
    {
      const element = await driver.findElement(By.id("btnBorrar"))
      await driver.actions({ bridge: true}).doubleClick(element).perform()
    }
    await driver.findElement(By.id("btnBorrar")).click()
    await driver.findElement(By.id("btnBorrar")).click()
    await driver.findElement(By.id("btnBorrar")).click()
    await driver.findElement(By.id("btnBorrar")).click()
    await driver.findElement(By.id("btnBorrar")).click()
    assert(await driver.findElement(By.id("txtPantalla")).getText() == "0")
    await driver.findElement(By.css(".btnDigito:nth-child(14)")).click()
    await driver.findElement(By.css(".btnDigito:nth-child(15)")).click()
    await driver.findElement(By.css(".btnDigito:nth-child(16)")).click()
    assert(await driver.findElement(By.id("txtPantalla")).getText() == "123")
    await driver.findElement(By.id("btnIniciar")).click()
    assert(await driver.findElement(By.id("txtPantalla")).getText() == "0")
  })
  it('dígitos calculadora por codigo', async function() {
    await driver.get("http://192.168.1.156:8181/")
    await driver.manage().window().setRect({ width: 1212, height: 667 })
    await driver.findElement(By.linkText("Calculadora")).click()
    vars["digito"] = "9"
    do {
      await driver.findElement(By.css("input[value=\'vars["digito"]\']")).click()
      vars["digito"] = await driver.executeScript("return arguments[0] - 1", vars["digito"])
    } while(!!await driver.executeScript("return (arguments[0] >= 0)", vars["digito"]))
    await driver.findElement(By.css("input[value=\',\']")).click()
    await driver.findElement(By.css("input[value=\'1\']")).click()
    await driver.findElement(By.css("input[value=\'±\']")).click()
    assert(await driver.findElement(By.id("txtPantalla")).getText() == "-9876543210,1")
    const times = 4
    for(let i = 0; i < times; i++) {
      await driver.findElement(By.id("btnBorrar")).click()
    }
    assert(await driver.findElement(By.id("txtPantalla")).getText() == "-98765432")
    const times = 8
    for(let i = 0; i < times; i++) {
      await driver.findElement(By.id("btnBorrar")).click()
    }
    assert(await driver.findElement(By.id("txtPantalla")).getText() == "0")
    await driver.findElement(By.css(".btnDigito:nth-child(14)")).click()
    await driver.findElement(By.css(".btnDigito:nth-child(15)")).click()
    await driver.findElement(By.css(".btnDigito:nth-child(16)")).click()
    assert(await driver.findElement(By.id("txtPantalla")).getText() == "123")
    await driver.findElement(By.id("btnIniciar")).click()
    assert(await driver.findElement(By.id("txtPantalla")).getText() == "0")
    vars["secuencia"] = "1+2+3="
    const collection = vars["secuencia"]
    for (let i = 0; i < collection.length - 1; i++) {
      vars["btn"] = vars["secuencia"][i]
      await driver.findElement(By.css("input[value=\'vars["btn"]\']")).click()
    }
    assert(await driver.findElement(By.id("txtPantalla")).getText() == "6")
    await driver.findElement(By.id("btnIniciar")).click()
    vars["secuencia"] = await driver.executeScript("return [\"3\",\"3\",\"-\",\"2\",\"1\",\"=\"]")
    const collection = vars["secuencia"]
    for (let i = 0; i < collection.length - 1; i++) {
      vars["btn"] = vars["secuencia"][i]
      await driver.findElement(By.css("input[value=\'vars["btn"]\']")).click()
    }
    assert(await driver.findElement(By.id("txtPantalla")).getText() == "12")
  })
  it('emp login', async function() {
    logout()
    await driver.findElement(By.id("txtUsuario")).sendKeys("emp@kk.kk")
    await driver.findElement(By.id("txtPassword")).sendKeys("P@$$w0rd")
    await driver.findElement(By.css(".fa-sign-in-alt")).click()
    assert(await driver.findElement(By.id("userData")).getText() == "Hola Empleado")
  })
  it('login admin', async function() {
    logout()
    await driver.findElement(By.id("txtUsuario")).sendKeys("adm@kk.kk")
    await driver.findElement(By.id("txtPassword")).sendKeys("P@$$w0rd")
    await driver.findElement(By.css(".fa-sign-in-alt")).click()
    assert(await driver.findElement(By.id("userData")).getText() == "Hola Administrador")
    await driver.findElement(By.css(".fa-sign-out-alt")).click()
  })
  it('login con fragmentos', async function() {
    admlogin()
    logout()
  })
  it('login incorrecto', async function() {
    await driver.get("http://192.168.1.156:8181/")
    await driver.findElement(By.css(".fa-sign-in-alt")).click()
    assert(await driver.switchTo().alert().getText() == "ERRORES:\n   Usuario: Completa este campo\n   Contraseña: Completa este campo")
    await driver.findElement(By.id("txtUsuario")).sendKeys("admin")
    await driver.findElement(By.css(".fa-sign-in-alt")).click()
    assert(await driver.switchTo().alert().getText() == "ERRORES:\n   Contraseña: Completa este campo")
    await driver.findElement(By.id("txtPassword")).sendKeys("kk")
    await driver.findElement(By.css(".fa-sign-in-alt")).click()
    assert(await driver.switchTo().alert().getText() == "ERRORES:\n   Contraseña: Utiliza un formato que coincida con el solicitado")
    await driver.findElement(By.css(".input-group")).click()
    await driver.findElement(By.id("txtPassword")).sendKeys("P@$$w0rs")
    await driver.findElement(By.id("txtUsuario")).sendKeys("admin@kk")
  })
  it('logout', async function() {
    await driver.get("http://192.168.1.156:8181/")
    vars["usuario"] = await driver.findElement(By.id("userData")).getText()
    if (!!await driver.executeScript("return (arguments[0] != \'\')", vars["usuario"])) {
      await driver.findElement(By.css(".fa-sign-out-alt")).click()
    }
  })
  it('CRUD', async function() {
    await driver.get("http://192.168.1.156:8181/contactos")
    await driver.manage().window().setRect({ width: 1212, height: 667 })
    await driver.findElement(By.linkText("Contactos")).click()
    await driver.findElement(By.css(".text-right > .btn")).click()
    await driver.findElement(By.id("id")).sendKeys("0")
    await driver.findElement(By.id("id")).click()
    await driver.findElement(By.id("nombre")).click()
    await driver.findElement(By.id("nombre")).sendKeys("1111")
    await driver.findElement(By.id("btnEnviar")).click()
    assert(await driver.findElement(By.css("tr:nth-child(2) .container .btn")).getText() == "Sr. 1111")
    await driver.findElement(By.css("tr:nth-child(2) .btn-success > .fas")).click()
    await driver.findElement(By.id("nombre")).click()
    await driver.findElement(By.id("apellidos")).click()
    await driver.findElement(By.id("apellidos")).sendKeys("Grillo")
    await driver.findElement(By.id("telefono")).sendKeys("555123456")
    await driver.findElement(By.id("email")).sendKeys("pepito@grillo")
    await driver.findElement(By.id("nacimiento")).click()
    await driver.findElement(By.id("nacimiento")).sendKeys("2022-09-05")
    await driver.findElement(By.id("avatar")).click()
    await driver.findElement(By.id("avatar")).sendKeys("https://randomuser.me/api/portraits/men/4.jpg")
    await driver.findElement(By.id("avatar")).click()
    await driver.findElement(By.id("avatar")).sendKeys("https://randomuser.me/api/portraits/men/44.jpg")
    await driver.findElement(By.id("btnEnviar")).click()
    assert(await driver.findElement(By.id("alertError")).getText() == "ERROR: 400: request.body.telefono should match pattern \\\"^(\\\\d{3}\\\\s){2}\\\\d{3}$\\\"\\\\n×")
    await driver.findElement(By.id("telefono")).click()
    await driver.findElement(By.id("telefono")).click()
    await driver.findElement(By.id("telefono")).sendKeys("555 123 456")
    await driver.findElement(By.id("btnEnviar")).click()
    assert(await driver.getTitle() == "Contactos")
    await driver.findElement(By.css("#btnCerrarError > span")).click()
    await driver.findElement(By.css("tr:nth-child(2) .btn-success > .fas")).click()
    await driver.findElement(By.id("apellidos")).click()
    await driver.findElement(By.id("apellidos")).sendKeys("Grillosss")
    await driver.findElement(By.id("btnVolver")).click()
    assert(await driver.findElement(By.css("tr:nth-child(2) .container .btn")).getText() == "Sr. 1111 Grillo")
    await driver.findElement(By.css("tr:nth-child(2) .btn-info")).click()
    assert(await driver.findElement(By.css("h4")).getText() == "Sr. 1111 Grillo")
    await driver.findElement(By.css(".btn:nth-child(4)")).click()
    await driver.findElement(By.css("tr:nth-child(2) .far")).click()
    assert(await driver.switchTo().alert().getText() == "¿Estas seguro?")
    await driver.switchTo().alert().dismiss()
    assert(await driver.findElement(By.css("tr:nth-child(2) .container .btn")).getText() == "Sr. 1111 Grillo")
    await driver.findElement(By.css("tr:nth-child(2) .far")).click()
    assert(await driver.switchTo().alert().getText() == "¿Estas seguro?")
    await driver.switchTo().alert().accept()
    assert(await driver.findElement(By.css("tr:nth-child(2) .container .btn")).getText() == "Dr. Adolf Dunster")
  })
})
