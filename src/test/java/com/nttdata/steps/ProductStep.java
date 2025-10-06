package com.nttdata.steps;

import com.nttdata.model.Product;
import com.nttdata.page.ProductPage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductStep {
    private WebDriver driver;
    private Product productData;

    public ProductStep(WebDriver driver, Product productData) {
        this.driver = driver;
        this.productData = productData;
    }

    public void selectCategorySubcategory(String category, String subCategory) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            WebElement categoryElement = wait.until(ExpectedConditions.visibilityOfElementLocated(ProductPage.category(category)));
            categoryElement.click();
        } catch (TimeoutException e) {
            throw new AssertionError(" La categoría '" + category + "' no existe en la página.");
        }

        try {
            WebElement subCategoryElement = wait.until(ExpectedConditions.visibilityOfElementLocated(ProductPage.subCategory(subCategory)));
            subCategoryElement.click();
        } catch (TimeoutException e) {
            throw new AssertionError("La subcategoría '" + subCategory + "' no existe dentro de la categoría '" + category + "'.");
        }
    }

    public void addProducts(int quantity) {
        driver.findElement(ProductPage.firstProduct).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));

        wait.until(ExpectedConditions.visibilityOfElementLocated(ProductPage.quantityProduct));
        WebElement quantityInput = driver.findElement(ProductPage.quantityProduct);
        quantityInput.click();
        quantityInput.sendKeys(Keys.CONTROL + "a");
        quantityInput.sendKeys(Keys.BACK_SPACE);
        quantityInput.sendKeys(String.valueOf(quantity));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(444));
        this.driver.findElement(ProductPage.addCartButton).click();
    }

    public String productConfirmationPopup() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement popupTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(ProductPage.titlePopup));
        return popupTitle.getText();
    }

    public void validPopupTotalAmount() {
        String productPrice = driver.findElement(ProductPage.productPrice).getText().trim().replace("S/", "").trim();
        double unitPrice = Double.parseDouble(productPrice);

        String productQuantity = driver.findElement(ProductPage.productQuantity).getText().trim();
        int quantity = Integer.parseInt(productQuantity);

        String totalPrice = driver.findElement(ProductPage.totalPrice).getText().trim().replace("S/", "").trim();
        double totalPriceProducts = Double.parseDouble(totalPrice);

        double expectTotal = unitPrice * quantity;

        Assertions.assertEquals(expectTotal, totalPriceProducts);

        productData.setPrice(unitPrice);
        productData.setQuantity(quantity);
        productData.setTotal(totalPriceProducts);

    }

    public void finalizePurchase() {
        this.driver.findElement(ProductPage.checkOutButton).click();
    }
}
