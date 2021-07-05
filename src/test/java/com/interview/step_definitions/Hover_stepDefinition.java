package com.interview.step_definitions;

import com.interview.pages.HoverPage;
import com.interview.utilities.ConfigurationReader;
import com.interview.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;

public class Hover_stepDefinition {
HoverPage hoverPage=new HoverPage();
    Actions actions = new Actions(Driver.getDriver());
    @Given("user is on the Mouse Hover page")
    public void user_is_on_the_mouse_hover_page() {
        String url= ConfigurationReader.getProperty("baseUrl");
        Driver.getDriver().get(url+"/hovers");
    }




    @When("mouse hover on each image")
    public void mouse_hover_on_each_image() {
List<WebElement>images=hoverPage.images;
        for (WebElement eachImage : images) {
            actions.moveToElement(eachImage).perform();


//
//            System.out.println("hoverPage.texts1.getText() = " + hoverPage.texts1.getText());
//            System.out.println("hoverPage.texts2.getText() = " + hoverPage.texts2.getText());
//            System.out.println("hoverPage.texts3.getText() = " + hoverPage.texts3.getText());



        }
        //practice github
        //
        //
        //
        //


        for (int i = 0; i <images.size(); i++) {
            actions.moveToElement(images.get(i)).perform();

            System.out.println("images.get(i).getText() = " + images.get(i).getText());
            System.out.println("(\"name: user\"+i) = " + ("name: user" + (i+1)));

            //    if(images.get(i).getText().equals("name: user"))
              Assert.assertTrue(images.get(i).getText().contains("name: user"+(i+1)));

        }


    }
    @Then("additional information is displayed for each image.")
    public void additional_information_is_displayed_for_each_image() {
        //you can see the text after hover
        System.out.println("hoverPage.texts1.getText() = " + hoverPage.texts1.getText());
        System.out.println("hoverPage.texts2.getText() = " + hoverPage.texts2.getText());
        System.out.println("hoverPage.texts3.getText() = " + hoverPage.texts3.getText());
        //right now mouse is hover on the image3 so you can see only see text3
//Assert.assertTrue(hoverPage.texts1.getText().equals("name: user1"));
//        Assert.assertTrue(hoverPage.texts2.getText().equals("name: user2"));
//       Assert.assertTrue(hoverPage.texts3.getText().equals("name: user3"));
    }

}
