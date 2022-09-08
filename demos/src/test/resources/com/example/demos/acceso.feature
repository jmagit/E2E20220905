# language: es

@browser
Característica: Control de acceso

  Escenario: Acceder como administrador
  Dado que estoy en la página principal
  Cuando pongo "adm@kk.kk" como usuario
  Y pongo "P@$$w0rd" como contraseña
  Entonces me saluda con "Hola Administrador"

  Escenario: Acceder como empleado
  Dado que estoy en la página principal
  Cuando pongo "emp@kk.kk" como usuario
  Y pongo "P@$$w0rd" como contraseña
  Entonces me saluda con "Hola Empleado"

