package com.nttdata.steps;

import com.nttdata.core.DriverManager;
import com.nttdata.page.InventoryPage;
import com.nttdata.page.LoginPage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.nttdata.core.DriverManager.*;


public class LoginSteps {

    private WebDriver driver;

    //constructor
    public LoginSteps(WebDriver driver){
        this.driver = driver;
    }


    /**
     * Hacer click en el link "Sign In"
     */
    public void clickSignInLink() throws InterruptedException {

        WebElement signInElement = driver.findElement(LoginPage.signInLink);
        signInElement.click();
    }

    /**
     * Hacer click en el link "No Account? Create one here"
     */
    public void clickCreateAccountLink() {
        WebElement createAccountElement = driver.findElement(LoginPage.createAccountLink);
        createAccountElement.click();
    }

    /**
     * Seleccionar el radio button "Mr"
     */
    public void selectMrRadioButton() {
        WebElement mrRadioElement = driver.findElement(LoginPage.mrRadioButton);
        if (!mrRadioElement.isSelected()) {
            mrRadioElement.click();
        }
    }

    /**
     * Llenar los campos de nombre y apellido
     * @param firstName el nombre
     * @param lastName el apellido
     */
    public void fillNameAndLastName(String firstName, String lastName) {
        WebElement firstNameElement = driver.findElement(LoginPage.firstNameInput);
        WebElement lastNameElement = driver.findElement(LoginPage.lastNameInput);

        firstNameElement.clear();
        firstNameElement.sendKeys(firstName);

        lastNameElement.clear();
        lastNameElement.sendKeys(lastName);
    }

    /**
     * Llenar los campos de correo electrónico y contraseña
     * @param email el correo electrónico
     * @param password la contraseña
     */
    public void fillEmailAndPassword(String email, String password) {
        WebElement emailElement = driver.findElement(LoginPage.emailInput);
        WebElement passwordElement = driver.findElement(LoginPage.passwordInput);

        emailElement.clear();
        emailElement.sendKeys(email);

        passwordElement.clear();
        passwordElement.sendKeys(password);
    }

    /**
     * Presionar el checkbox de Términos y condiciones
     */
    public void clickTermsCheckbox() {
        try {
            // Buscar todos los checkboxes en la página y marcar el primero que no esté seleccionado
            // Esto es útil cuando no conocemos los IDs exactos pero sabemos que hay checkboxes
            java.util.List<WebElement> checkboxes = driver.findElements(By.cssSelector("input[type='checkbox']"));

            if (!checkboxes.isEmpty()) {
                // Marcar el primer checkbox que no esté seleccionado
                for (WebElement checkbox : checkboxes) {
                    if (!checkbox.isSelected()) {
                        // Verificar si es visible y clickeable
                        if (checkbox.isDisplayed() && checkbox.isEnabled()) {
                            checkbox.click();
                            System.out.println("Checkbox de términos marcado exitosamente");
                            return;
                        }
                    }
                }
                System.out.println("Todos los checkboxes ya están marcados");
            } else {
                throw new RuntimeException("No se encontraron checkboxes en la página");
            }
        } catch (Exception e) {
            System.out.println("Error al marcar checkbox de términos: " + e.getMessage());
            throw new RuntimeException("No se pudo marcar el checkbox de términos en QA Lab");
        }
    }

    /**
     * Presionar el checkbox de Privacidad de datos del cliente y marcar TODOS los checkboxes
     */
    public void clickPrivacyCheckbox() {
        try {
            // Buscar todos los checkboxes en la página y marcarlos todos
            java.util.List<WebElement> checkboxes = driver.findElements(By.cssSelector("input[type='checkbox']"));

            if (!checkboxes.isEmpty()) {
                int checkboxesMarcados = 0;
                // Marcar todos los checkboxes que no estén seleccionados
                for (WebElement checkbox : checkboxes) {
                    if (!checkbox.isSelected()) {
                        // Verificar si es visible y clickeable
                        if (checkbox.isDisplayed() && checkbox.isEnabled()) {
                            checkbox.click();
                            checkboxesMarcados++;
                            System.out.println("Checkbox marcado (" + checkboxesMarcados + ")");
                        }
                    }
                }
                System.out.println("Total de checkboxes marcados: " + checkboxesMarcados);
                if (checkboxesMarcados == 0) {
                    System.out.println("Todos los checkboxes ya estaban seleccionados");
                }
            } else {
                throw new RuntimeException("No se encontraron checkboxes en la página");
            }
        } catch (Exception e) {
            System.out.println("Error al marcar checkboxes de privacidad: " + e.getMessage());
            throw new RuntimeException("No se pudo marcar los checkboxes de privacidad en QA Lab");
        }
    }

    /**
     * Hacer click en el botón "Save"
     */
    public void clickSaveButton() {
        WebElement saveButtonElement = driver.findElement(LoginPage.saveButton);
        saveButtonElement.click();
    }

    /**
     * Obtener el saludo del usuario en la parte superior derecha de la página
     * @return el texto del saludo con el nombre y apellido
     */
    public String getUserGreeting() {
        try {
            // Intentar primero con el localizador original
            WebElement greetingElement = driver.findElement(LoginPage.userGreeting);
            String greeting = greetingElement.getText();

            // Si el saludo contiene "Sign in", intentar buscar otros elementos
            if (greeting != null && greeting.contains("Sign in")) {
                // Buscar por otros posibles localizadores de usuario logueado
                try {
                    WebElement userElement = driver.findElement(By.cssSelector(".user-info span, .account span, .logged-in-user"));
                    return userElement.getText();
                } catch (Exception e2) {
                    // Si no encontramos elementos específicos, devolver el texto actual
                    return greeting;
                }
            }

            return greeting;
        } catch (Exception e) {
            // Si no se encuentra el elemento, devolver un mensaje por defecto
            return "Usuario no encontrado";
        }
    }
}
