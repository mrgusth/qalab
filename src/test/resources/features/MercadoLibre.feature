#language: es
Característica: MercadoLibre
@busquedaProducto
  Escenario: Busqueda de Productos
    Dado estoy en la página de MercadoLibre
    Cuando busco un producto "Televisores Led 40 pulgadas"
    Entonces una lista de resultados
    Y valido el primer titulo del resultado sea "Smart Tv 40\" S5400a Led Fhd Android Negro TCL"