package com.nttdata.core;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chromium.ChromiumDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverManager {
    private static WebDriver driver;
    private static Scenario scenario;

    public static WebDriver getDriver() {
        return driver;
    }

    @Before(order = 0)
    public void setUp() {
        // Detectar SO y configurar el path absoluto del driver
        String os = System.getProperty("os.name").toLowerCase();
        String projectPath = System.getProperty("user.dir");

        // INTENTAR PRIMERO CON FIREFOX (más estable para QA Lab)
        try {
            FirefoxOptions firefoxOptions = new FirefoxOptions();

            // Opciones para saltar verificaciones SSL
            firefoxOptions.addArguments("--ignore-certificate-errors");
            firefoxOptions.addArguments("--ignore-ssl-errors");

            // Configurar preferencias para deshabilitar verificaciones
            firefoxOptions.addPreference("dom.webnotifications.enabled", false);
            firefoxOptions.addPreference("dom.disable_beforeunload", true);
            firefoxOptions.addPreference("browser.tabs.warnOnClose", false);
            firefoxOptions.addPreference("browser.tabs.warnOnOpen", false);
            firefoxOptions.addPreference("security.mixed_content.block_active_content", false);
            firefoxOptions.addPreference("security.mixed_content.block_display_content", false);
            firefoxOptions.addPreference("browser.download.manager.showWhenStarting", false);

            if (os.contains("win")) {
                String geckoDriverPath = projectPath + "\\drivers\\geckodriver.exe";
                System.setProperty("webdriver.gecko.driver", geckoDriverPath);
                System.out.println("Configurando GeckoDriver path: " + geckoDriverPath);
            } else if (os.contains("mac")) {
                String geckoDriverPath = projectPath + "/drivers/geckodriver";
                System.setProperty("webdriver.gecko.driver", geckoDriverPath);
                System.out.println("Configurando GeckoDriver path: " + geckoDriverPath);
            }

            driver = new FirefoxDriver(firefoxOptions);
            driver.manage().window().maximize();
            System.out.println("FirefoxDriver inicializado correctamente para QA Lab");

        } catch (Exception firefoxException) {
            System.out.println("Error al inicializar FirefoxDriver: " + firefoxException.getMessage());
            System.out.println("Intentando con Chrome como alternativa...");

            try {
                // Chrome como segunda opción
                ChromeOptions chromeOptions = new ChromeOptions();

                // Opciones mínimas para evitar problemas
                chromeOptions.addArguments("--no-sandbox");
                chromeOptions.addArguments("--disable-dev-shm-usage");
                chromeOptions.addArguments("--disable-gpu");
                chromeOptions.addArguments("--ignore-certificate-errors");
                chromeOptions.addArguments("--ignore-ssl-errors");
                chromeOptions.addArguments("--disable-web-security");
                chromeOptions.addArguments("--allow-running-insecure-content");
                chromeOptions.addArguments("--headless"); // Modo headless para evitar problemas de DevTools

                if (os.contains("win")) {
                    String chromeDriverPath = projectPath + "\\drivers\\chromedriver.exe";
                    System.setProperty("webdriver.chrome.driver", chromeDriverPath);
                    System.out.println("Configurando ChromeDriver path: " + chromeDriverPath);
                } else if (os.contains("mac")) {
                    String chromeDriverPath = projectPath + "/drivers/chromedriver";
                    System.setProperty("webdriver.chrome.driver", chromeDriverPath);
                    System.out.println("Configurando ChromeDriver path: " + chromeDriverPath);
                }

                driver = new ChromeDriver(chromeOptions);
                driver.manage().window().maximize();
                System.out.println("ChromeDriver inicializado en modo headless como alternativa");

            } catch (Exception chromeException) {
                System.out.println("Error también con ChromeDriver: " + chromeException.getMessage());
                throw new RuntimeException("No se pudo inicializar ningún navegador. Firefox error: " +
                    firefoxException.getMessage() + ". Chrome error: " + chromeException.getMessage());
            }
        }
    }

    @Before(order = 1)
    public void setScenario(Scenario scenario) {
        this.scenario = scenario;
    }

    @After
    public void quitDriver() {
        driver.quit();
    }

    public static void screenShot() {
        byte[] evidencia = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        scenario.attach(evidencia, "image/png", "evidencias");
    }

    public static void scrollDown(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        // This  will scroll down the page by  1000 pixel vertical
        js.executeScript("window.scrollBy(0,1000)");
    }
    public static void esperaImplicita(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
