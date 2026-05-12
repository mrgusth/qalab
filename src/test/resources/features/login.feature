#language: es
@testfeature
Característica: Product - Store

  @crearCuenta
  Escenario: Realizar el login - Store
    Dado estoy en la página de la tienda "https://qalab.bensg.com/store"
    Cuando doy click en el link "Sign In"
    Y doy click en el link "No Account? Create one here"
    Y doy click en el radio button de Mr
    Y lleno el nombre: "gustavo" y apellido "martinez"
    Y uso el correo "gusmartinez@gmail.com" y la contraseña "!@#123aBcWD2"
    Y presiono el checkbox de Terms and conditions
    Y presiono el checkbox de Customer data privacy
    Entonces presiono el boton de "Save"
    Y deberia de visualizar mi nombre y apellido en la parte superior derecha de la pagina