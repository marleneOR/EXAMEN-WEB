package com.nttdata.page;

import org.openqa.selenium.By;

public class CartPage {

    public static By cartTitle = By.cssSelector(".cart-container .card-block h1.h1");

    public static By productPriceCart = By.cssSelector("div.current-price span.price");
    public static By productQuantityCart = By.name("product-quantity-spin");
    public static By totalPriceCart = By.cssSelector("div.cart-summary-line.cart-total span.value");
}
