package com.interview.step_definitions;

import com.interview.pages.FileUploadPage;
import com.interview.utilities.ConfigurationReader;
import com.interview.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

public class fileUpload_stepDefinition {
    FileUploadPage fileUploadPage=new FileUploadPage();
    String path ="/Users/minibae/Downloads/some-file.txt";
    @Given("user is on the file upload page")
    public void user_is_on_the_file_upload_page() {
        String url= ConfigurationReader.getProperty("baseUrl");
        Driver.getDriver().get(url+"/upload");
    }




    @When("user click chooseFIle button and select file")
    public void user_click_choose_f_ile_button_and_select_file() {
fileUploadPage.chooseFileButton.sendKeys(path);
    }
    @When("click the upload button")
    public void click_the_upload_button() {
fileUploadPage.uploadButton.click();
    }
    @Then("user will see File is Uploaded!")
    public void user_will_see_file_is_uploaded() {
        Assert.assertTrue(fileUploadPage.fileUploadedMessage.getText().equals("File Uploaded!"));
    }

    @When("user click chooseFIle button and select file with drag and drop")
    public void userClickChooseFIleButtonAndSelectFileWithDragAndDrop() {
// drop the file
        DropFile(new File(path),fileUploadPage.dropArea, 0, 0);


    }

    public void DropFile(File filePath, WebElement target, int offsetX, int offsetY) {
        if (!filePath.exists())
            throw new WebDriverException("File not found: " + filePath.toString());

        JavascriptExecutor jse = (JavascriptExecutor) Driver.getDriver();

        String JS_DROP_FILE =
                "var target = arguments[0]," +
                        "    offsetX = arguments[1]," +
                        "    offsetY = arguments[2]," +
                        "    document = target.ownerDocument || document," +
                        "    window = document.defaultView || window;" +
                        "" +
                        "var input = document.createElement('INPUT');" +
                        "input.type = 'file';" +
                        "input.style.display = 'none';" +
                        "input.onchange = function () {" +
                        "  var rect = target.getBoundingClientRect()," +
                        "      x = rect.left + (offsetX || (rect.width >> 1))," +
                        "      y = rect.top + (offsetY || (rect.height >> 1))," +
                        "      dataTransfer = { files: this.files };" +
                        "" +
                        "  ['dragenter', 'dragover', 'drop'].forEach(function (name) {" +
                        "    var evt = document.createEvent('MouseEvent');" +
                        "    evt.initMouseEvent(name, !0, !0, window, 0, 0, 0, x, y, !1, !1, !1, !1, 0, null);" +
                        "    evt.dataTransfer = dataTransfer;" +
                        "    target.dispatchEvent(evt);" +
                        "  });" +
                        "" +
                        "  setTimeout(function () { document.body.removeChild(input); }, 25);" +
                        "};" +
                        "document.body.appendChild(input);" +
                        "return input;";

        WebElement input = (WebElement) jse.executeScript(JS_DROP_FILE, target, offsetX, offsetY);
        input.sendKeys(filePath.getAbsoluteFile().toString());
        WebDriverWait wait=new WebDriverWait(Driver.getDriver(),5);
        wait.until(ExpectedConditions.stalenessOf(input));
    }


}
