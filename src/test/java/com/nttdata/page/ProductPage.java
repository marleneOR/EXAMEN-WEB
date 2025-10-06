package com.nttdata.page;

import org.openqa.selenium.By;

public class ProductPage {
    public static By accountLink = By.cssSelector("a.account");

    public static By category(String category) {
        return By.xpath("//ul[@id='top-menu']//a[contains(normalize-space(.),'" + category + "')]");
    }

    public static By subCategory(String subcategory) {
        return By.xpath("//div[@class='block-categories']//ul[@class='category-sub-menu']//a[contains(normalize-space(.),'" + subcategory + "')]");
    }

    public static By firstProduct = By.xpath("(//div[@class='products row']//h2[@class='h3 product-title']/a)[1]");
    public static By quantityProduct = By.id("quantity_wanted");
    public static By addCartButton = By.xpath("//button[@data-button-action='add-to-cart']");
    public static By titlePopup = By.id("myModalLabel");

    public static By productPrice = By.cssSelector("#blockcart-modal .product-price");
    public static By productQuantity = By.cssSelector("#blockcart-modal .product-quantity strong");
    public static By totalPrice = By.cssSelector("#blockcart-modal .product-total .value");
    public static By checkOutButton = By.cssSelector("#blockcart-modal .cart-content-btn a.btn-primary");
}
