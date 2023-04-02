package tests;


import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase{

    @BeforeMethod
    public void precondition(){
        if(app.getHelperUser().isLogged()){
            app.getHelperUser().logout();
        }
    }
    @Test
    public void loginSuccess1(){
        User user = new User().setEmail("irena9693541@gmail.com").setPassword("04Darisopa09$");

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage(),"Logged in success");
       

    }
    @Test
    public void loginSuccess(){
     app.getHelperUser().openLoginRegistrationForm();
    app.getHelperUser().fillLoginRegistrationForm("irena9693541@gmail.com","04Darisopa09$");
    app.getHelperUser().submit();
    Assert.assertEquals(app.getHelperUser().getMessage(),"Logged in success");


    }
    @Test
    public void loginSuccessModel(){
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("irena9693541@gmail.com","04Darisopa09$");
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage(),"Logged in success");


    }
    @Test
    public void loginUnregisteredUser(){
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("Luck@gmail.com","04Darisopa09$");
        app.getHelperUser().submit();
     Assert.assertEquals(app.getHelperUser().getMessage(),"\"Login or Password incorrect\" ");

    }

    @Test
    public void loginWrongEmail(){
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("irena9693541gmail.com","04Darisopa09$");
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getErrorText(),"It is not look like email");
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());


    }

    @Test
    public void loginWrongPassword(){
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("irena9693541@gmail.com","04darisopa09");
        app.getHelperUser().submit();
    Assert.assertEquals(app.getHelperUser().getMessage(),"\"Login or Password incorrect\" ");
        
    }


    @AfterMethod
    public void postCondition() {
        app.getHelperUser().closeWindow();
    }
}
