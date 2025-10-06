package com.nttdata.steps;

import com.nttdata.page.LoginPage;
import com.nttdata.page.ProductPage;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginStep {
    private WebDriver driver;

    public LoginStep(WebDriver driver) {
        this.driver = driver;
    }

    public void typeUser(String user) {
        driver.findElement(LoginPage.loginLink).click();
        WebElement userInputElement = driver.findElement(LoginPage.userInput);
        userInputElement.sendKeys(user);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPage.loginButton));
    }

    public void typePassword(String password) {
        this.driver.findElement(LoginPage.passInput).sendKeys(password);
    }

    public void login() {
        driver.findElement(LoginPage.loginButton).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        try {
            WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPage.helpMessage));
            if (errorElement.getText().contains("Error de autenticación")) {
                throw new AssertionError("Login fallido: Usuario o contraseña incorrectos.");
            }
        } catch (TimeoutException e) {
            try {
                wait.until(ExpectedConditions.visibilityOfElementLocated(ProductPage.accountLink));
            } catch (TimeoutException ex) {
                throw new AssertionError("Login fallido sin mensaje de error.");
            }
        }
    }


}

