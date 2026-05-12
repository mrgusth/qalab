package com.nttdata.page;

import com.nttdata.core.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {


    //Localizadores de elementos - QA Lab Store (Crear Cuenta)
    public static By signInLink = By.className("user-info");
    public static By createAccountLink = By.cssSelector("a[href*='registration']");
    public static By mrRadioButton = By.id("field-id_gender-1");
    public static By firstNameInput = By.id("field-firstname");
    public static By lastNameInput = By.id("field-lastname");
    public static By emailInput = By.id("field-email");
    public static By passwordInput = By.id("field-password");

    // Checkboxes - selectores para Prestashop (común en QA Lab)
    public static By termsCheckbox = By.cssSelector("#field-agree");
    public static By privacyCheckbox = By.cssSelector("#field-customer_notice");

    // Alternativas más genéricas
    public static By termsCheckboxAlt = By.cssSelector("input[name='agree']");
    public static By privacyCheckboxAlt = By.cssSelector("input[name='customer_notice']");

    // Selectores XPath como último recurso
    public static By termsCheckboxXpath = By.xpath("//input[contains(@id, 'agree')]");
    public static By privacyCheckboxXpath = By.xpath("//input[contains(@id, 'customer')]");

    public static By saveButton = By.cssSelector("button[type='submit']");
    public static By userGreeting = By.className("user-info");

}
