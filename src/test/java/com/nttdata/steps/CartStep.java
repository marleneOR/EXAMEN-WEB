package com.nttdata.steps;

import com.nttdata.model.Product;
import com.nttdata.page.CartPage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

public class CartStep {
    private WebDriver driver;
    private Product productData;

    public CartStep(WebDriver driver, Product productData ) {
        this.driver = driver;
        this.productData = productData;
    }

    public String getTitleCart() {
        return this.driver.findElement(CartPage.cartTitle).getText();
    }

    public void validPopupTotalAmountCart() {
        String productPrice = driver.findElement(CartPage.productPriceCart).getText().trim().replace("S/", "").trim();
        double unitPriceCart = Double.parseDouble(productPrice);

        String productQuantity = driver.findElement(CartPage.productQuantityCart).getAttribute("value").trim();
        int quantityCart = Integer.parseInt(productQuantity);

        String totalPrice = driver.findElement(CartPage.totalPriceCart).getText().trim().replace("S/", "").trim();
        double totalPriceCart = Double.parseDouble(totalPrice);

        double expectTotalCart = unitPriceCart * quantityCart;

        Assertions.assertEquals(expectTotalCart, totalPriceCart);

        double expectUnitPrice = productData.getPrice();
        int expectProductQuantity = productData.getQuantity();
        double expectTotalProducts = productData.getTotal();
        Assertions.assertEquals(expectUnitPrice, unitPriceCart);
        Assertions.assertEquals(expectProductQuantity, quantityCart);
        Assertions.assertEquals(expectTotalProducts, totalPriceCart);

    }

}
