package com.nttdata.stepsdefinitions;

import com.nttdata.steps.MercadoLibreSteps;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

import static com.nttdata.core.DriverManager.*;

public class MercadoLibreStepsDef {

    private WebDriver driver;

    @Dado("estoy en la página de MercadoLibre")
    public void estoy_en_la_pagina_de_mercadolibre() {
        driver = getDriver();
        driver.get("https://www.mercadolibre.com.pe/");
        screenShot();
    }

    @Cuando("busco un producto {string}")
    public void busco_un_producto(String producto) {
        MercadoLibreSteps mercadoLibreSteps = new MercadoLibreSteps(driver);
        mercadoLibreSteps.buscarProducto(producto);
        screenShot();
    }

    @Entonces("una lista de resultados")
    public void una_lista_de_resultados() {
        MercadoLibreSteps mercadoLibreSteps = new MercadoLibreSteps(driver);
        boolean hayResultados = mercadoLibreSteps.hayResultados();
        Assertions.assertTrue(hayResultados, "No se encontraron resultados de búsqueda");
        screenShot();
    }

    @Y("valido el primer titulo del resultado sea {string}")
    public void valido_el_primer_titulo_del_resultado_sea(String expectedTitle) {
        MercadoLibreSteps mercadoLibreSteps = new MercadoLibreSteps(driver);
        String actualTitle = mercadoLibreSteps.getPrimerTitulo();
        Assertions.assertEquals(expectedTitle, actualTitle);
        screenShot();
    }
}
