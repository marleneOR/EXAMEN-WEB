package com.nttdata.page;

import org.openqa.selenium.By;

public class LoginPage {
    public static By loginLink = By.linkText("Iniciar sesión");
    public static By userInput = By.id("field-email");
    public static By passInput = By.id("field-password");
    public static By loginButton = By.id("submit-login");
    public static By helpMessage = By.cssSelector("li.alert.alert-danger");
}
