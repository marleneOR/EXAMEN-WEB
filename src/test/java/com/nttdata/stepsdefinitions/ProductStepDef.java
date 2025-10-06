package com.nttdata.stepsdefinitions;

import com.nttdata.core.DriverManager;
import com.nttdata.model.Product;
import com.nttdata.steps.CartStep;
import com.nttdata.steps.ProductStep;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

import static com.nttdata.core.DriverManager.screenShot;

public class ProductStepDef {
    private WebDriver driver = DriverManager.getDriver();
    private Product productData = new Product();

    private ProductStep productStep = new ProductStep(driver, productData);
    private CartStep cartStep = new CartStep(driver, productData);

    @When("navego a la categoria {string} y subcategoria {string}")
    public void navegoALaCategoriaYSubcategoria(String category, String subCategory) {
        productStep.selectCategorySubcategory(category, subCategory);
        screenShot();
    }

    @And("agrego {int} unidades del primer producto al carrito")
    public void agregoUnidadesDelPrimerProductoAlCarrito(int quantity) {
        productStep.addProducts(quantity);
        screenShot();
    }

    @Then("valido en el popup la confirmación del producto agregado")
    public void validoEnElPopupLaConfirmaciónDelProductoAgregado() {
        String title = productStep.productConfirmationPopup();
        if (!title.isEmpty() && !Character.isLetterOrDigit(title.charAt(0))) {
            title = title.substring(1).trim();
        }
        String expectTitle = "Producto añadido correctamente a su carrito de compra";
        Assertions.assertEquals(expectTitle, title);

    }

    @And("valido en el popup que el monto total sea calculado correctamente")
    public void validoEnElPopupQueElMontoTotalSeaCalculadoCorrectamente() {
        productStep.validPopupTotalAmount();
        screenShot();
    }

    @When("finalizo la compra")
    public void finalizoLaCompra() {
        productStep.finalizePurchase();

    }

    @Then("valido el titulo de la pagina del carrito")
    public void validoElTituloDeLaPaginaDelCarrito() {
        String titleCart = cartStep.getTitleCart();
        String expectTitleCart = "Carrito";
        Assertions.assertEquals(expectTitleCart.toUpperCase(), titleCart.toUpperCase());
    }

    @And("vuelvo a validar el calculo de precios en el carrito")
    public void vuelvoAValidarElCalculoDePreciosEnElCarrito() {
        cartStep.validPopupTotalAmountCart();
        screenShot();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
