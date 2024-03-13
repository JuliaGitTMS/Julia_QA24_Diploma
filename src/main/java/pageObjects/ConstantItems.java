package pageObjects;

import org.openqa.selenium.By;

public enum ConstantItems {
    COOKIELINK("//a[@href='/lp/cookie/']"),
    ACCEPTCOOKIES("//button/span[contains(text(), 'Принять')]"),
    REJECTCOOKIES("//button/span[contains(text(), 'Отклонить')]"),
    CITYLINK("//div[@class='_message_32s20_11']"),
    CITYNAME("//div[@class='_message_32s20_11']//span[@class='notranslate']"),
    CITYLIST("//div[@class='_citiesList_1tu7k_34']//span"),
    CITYCLOSE("//div[@class='d-modal__close-button']"),
    WOMEN("//a[@data-genders='women']"),
    MEN("//a[@data-genders='men']"),
    KIDS("//a[@data-genders='kids']"),
    LOGIN("(//button)[1]"),
    BASKET("//*[@href='/checkout/cart/']"),
    SEARCHINPUT("//div[@class='_root_1su1z_2']/input"),
    SEARCHBUTTON("//div[@class='_root_1su1z_2']/button");

    private final String itemXpath;

    public String getItemXpath() {
        return itemXpath;
    }

    ConstantItems(String itemXpath) {
        this.itemXpath = itemXpath;
    }


}
