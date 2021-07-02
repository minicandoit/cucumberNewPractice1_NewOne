package com.interview.pages;

import com.interview.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class FloatingMenuPage {
    public FloatingMenuPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

  @FindBy(xpath = "//h3")
    public WebElement floatingMenu;
@FindBy(xpath = "(//a)[2]")
    public WebElement Home;
    @FindBy(xpath = "(//a)[3]")
    public WebElement News;
    @FindBy(xpath = "(//a)[4]")
    public WebElement Contact;
    @FindBy(xpath = "(//a)[5]")
    public WebElement About;
    @FindBy(xpath = "//a")
    public List<WebElement> floatingMenus;



}
