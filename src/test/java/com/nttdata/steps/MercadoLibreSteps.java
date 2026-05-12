package com.nttdata.steps;

import com.nttdata.page.MercadoLibrePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.nttdata.core.DriverManager.esperaImplicita;

public class MercadoLibreSteps {

    private WebDriver driver;

    // constructor
    public MercadoLibreSteps(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Buscar un producto en MercadoLibre
     * @param producto el producto a buscar
     */
    public void buscarProducto(String producto) {
        WebElement searchInputElement = driver.findElement(MercadoLibrePage.searchInput);
        searchInputElement.clear();
        searchInputElement.sendKeys(producto);

        WebElement searchButtonElement = driver.findElement(MercadoLibrePage.searchButton);
        searchButtonElement.click();

        // Espera explícita para que carguen los resultados
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(MercadoLibrePage.resultsList));
        esperaImplicita();
    }

    /**
     * Verificar si hay resultados de búsqueda
     * @return true si hay resultados, false en caso contrario
     */
    public boolean hayResultados() {
        return driver.findElements(MercadoLibrePage.resultsList).size() > 0;
    }

    /**
     * Obtener el título del primer resultado
     * @return el título del primer resultado
     */
    public String getPrimerTitulo() {
        WebElement titleElement = driver.findElement(MercadoLibrePage.firstResultTitle);
        return titleElement.getText();
    }
}
