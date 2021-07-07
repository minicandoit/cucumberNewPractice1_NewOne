package com.interview.step_definitions;

import com.interview.pages.NotificationMessagePage;
import com.interview.utilities.ConfigurationReader;
import com.interview.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

public class notificationMessage_stepDefinition {
NotificationMessagePage notificationMessagePage=new NotificationMessagePage();
    @Given("user is on the Notification Message Page")
    public void user_is_on_the_notification_message_page() {
        String url= ConfigurationReader.getProperty("baseUrl");
        Driver.getDriver().get(url+"/notification_message_rendered");
    }



    @When("user clicks on the Click Here link a multiple times")
    public void user_clicks_on_the_click_here_link_a_multiple_times() {
        List<String> messages=new ArrayList<>();
        for (int i = 0; i <7 ; i++) {
            notificationMessagePage.clickHereButton.click();
            System.out.println("notificationMessagePage.messages.getText() = " + notificationMessagePage.messages.getText());
            messages.add(notificationMessagePage.messages.getText());
            if(messages.get(i).contains("Action unsuccesful, please ")){
            Assert.assertTrue(messages.get(i).contains("Action unsuccesful, please"));
                System.out.println("pass");

            }else if(messages.get(i).contains("Action successfu")){
              Assert.assertTrue(messages.get(i).contains("Action successfu"));
                System.out.println("Action successful==>pass2");}else{
               Assert.assertTrue(messages.get(i).contains("Action Unsuccessfu"));
                System.out.println("\"pass3\" = " + "pass3");
            }
//
//            System.out.println("==========================================");
//            System.out.println("messages.get(i) = " + messages.get(i));
//            if(messages.get(i).equals("Action unsuccesful, please try again")){
//                Assert.assertTrue(messages.contains("Action unsuccesful, please try again"));
//                System.out.println("Action unsuccesful, please try again==>pass");
//            }else if(messages.get(i).equals("Action successful")){
//                Assert.assertTrue(messages.contains("Action successful"));
//                System.out.println("Action successful==>pass");
//            }else{
//             //   Assert.assertTrue(messages.contains("Action Unsuccessful"));
//                System.out.println("\"Action Unsuccessful\" = " + "Action Unsuccessful");
//            }

       }
        System.out.println("messages = " + messages);

      //  Assert.assertTrue(messages.contains("Action successful"));




    }
    @Then("asserts that one of the “Action Successful”, “Action unsuccessful, please try again”and “Action Unsuccessful” messages show on click")
    public void asserts_that_one_of_the_action_successful_action_unsuccessful_please_try_again_and_action_unsuccessful_messages_show_on_click() {

    }

}
