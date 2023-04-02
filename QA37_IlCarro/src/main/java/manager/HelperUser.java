package manager;

import models.User;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.time.Duration;

public class HelperUser extends HelperBase {
    public HelperUser(WebDriver wd) {
        super(wd);
    }

    public void openLoginRegistrationForm() {
        //  WebElement loginTab = wd.findElement(By.cssSelector("//a[@class='navigation-li-nk ngstar-inserted'][normalize-space()='Log in']"));
        //  loginTab.click();
        // click(By.cssSelector("a[href='/login?url=%2Fsearch']"));
        click(By.xpath("//a[text()=' Log in ']"));
    }

    public void fillLoginRegistrationForm(String email, String password) {
        type(By.id("email"), email);
        type(By.id("password"), password);

    }

    public void fillLoginRegistrationForm(User user) {
        type(By.id("email"), user.getEmail());
        type(By.id("password"), user.getPassword());
    }


    public void submit(){
        click(By.cssSelector("button[type='submit']"));
    }

    public String getMessage() {
        WebDriverWait wait = new WebDriverWait(wd, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(wd.findElement(By.cssSelector(".dialog-container"))));
        // pause(8000);
        return wd.findElement(By.cssSelector(".dialog-container>h2")).getText();
    }

    public void closeWindow() {
        if (isElementPresent(By.xpath("//button[text()='Ok']")))
            click(By.xpath("//button[text()='Ok']"));

    }

    public boolean isLogged() {
        return isElementPresent(By.xpath("//a[text()=' Logout ']"));

    }

    public void logout() {
        click(By.xpath("//a[text()=' Logout ']"));
    }

    public String getErrorText() {
        return wd.findElement(By.cssSelector("div.error")).getText();
    }

    public boolean isYallaButtonNotActive() {
        boolean res = isElementPresent(By.cssSelector("button[disabled]"));
        WebElement element = wd.findElement(By.cssSelector("button[type='submit'"));
        boolean result = element.isEnabled();
        return res && !result;
    }


    public void openRegistrationForm() {
        click(By.xpath("//a[text()='Sing up']"));
    }
    public void fillRegistrationForm(User user) {
        type(By.id("name"),user.getFirstName());
        type(By.id("LastName"),user.getLastName());
        type(By.id("email"),user.getEmail());
        type(By.id("password"), user.getPassword());

    }


    public void checkPolicy() {
       // click(By.id("terms of use"));
        click(By.cssSelector("label[for = 'terms-of-use']"));

    }
    public void checkPolicyXY(){
        if(!wd.findElement(By.id("terms-of-use")).isSelected()) {
            Dimension size = wd.manage().window().getSize();
            WebElement label = wd.findElement(By.cssSelector("label[for = 'terms-of-use']"));
            Dimension dimension = label.getSize();

            Rectangle rect = label.getRect();
            int w = rect.getWidth();
            int xOffset = -w / 2;
            Actions actions = new Actions(wd);
            actions.moveToElement(label, xOffset, 0).click().release().perform();
            Dimension size1 = label.getSize();


        }


    }

    public void login(User user) {
        openLoginRegistrationForm();
        fillLoginRegistrationForm(user);
        submit();
        closeWindow();
    }
}
