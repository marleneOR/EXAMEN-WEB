package com.nttdata.stepsdefinitions;

import com.nttdata.steps.LoginStep;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import org.openqa.selenium.WebDriver;

import static com.nttdata.core.DriverManager.getDriver;
import static com.nttdata.core.DriverManager.screenShot;

public class LoginStepDef {
    private WebDriver driver;

    @Given("estoy en la página de la tienda")
    public void estoyEnLaPáginaDeLaTienda() {
        driver = getDriver();
        driver.get("https://qalab.bensg.com/store/pe/");
        screenShot();
    }

    @And("me logueo con mi usuario {string} y clave {string}")
    public void meLogueoConMiUsuarioYClave(String user, String password) {
        LoginStep loginStep = new LoginStep(driver);
        loginStep.typeUser(user);
        loginStep.typePassword(password);
        loginStep.login();
        screenShot();
    }
}
