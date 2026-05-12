package com.nttdata.page;

import org.openqa.selenium.By;

public class MercadoLibrePage {

    // Localizadores de elementos para MercadoLibre
    public static By searchInput = By.cssSelector("input.nav-search-input");
    public static By searchButton = By.cssSelector("button.nav-search-btn");
    public static By resultsList = By.cssSelector("ol.ui-search-layout");
    public static By firstResultTitle = By.cssSelector("ol.ui-search-layout li:first-child h3.poly-component__title-wrapper a");

}
