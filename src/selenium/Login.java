package selenium;

import org.openqa.selenium.By;

public class Login extends StartFirefoxDriver
{
    private String user;
    private String pass;


    public Login()
    {

        // Start Firefox driver with saved settings
        driver = StartFirefox();
        driver.navigate().to( "https://www.gamebattles.com" );

    }


    public void checkForPrivacyPrompt()
    {

        if( driver.findElement( By.xpath( "/html/body/div[6]/div[2]/div/gb-privacy-policy-updated-dialog/div/gb-card/div[2]/button" ) ) != null )
        {
            driver.findElement( By.xpath( "/html/body/div[6]/div[2]/div/gb-privacy-policy-updated-dialog/div/gb-card/div[2]/button" ) )
                  .click();
        }
    }


    public boolean checkIfAlreadyLoggedIn()
    {
        
        
        if( driver.findElement( By.xpath( "/html/body/gb-root/gb-main-layout/div/div/sh-header-container/sh-header/div/div/div[2]/button[1]" ) )
                  .getText()
                  .contains( "Log" ) )
        {
            System.out.println("Test");
            return false;
        }

        return true;
    }

    public void loginUser()
    {

        checkForPrivacyPrompt();

        if( !checkIfAlreadyLoggedIn() )
        {
            driver.findElement( By.xpath( "/html/body/gb-root/gb-main-layout/div/div/sh-header-container/sh-header/div/div/div[2]/button[1]" ) )
                  .click();
            driver.findElement( By.xpath( "//*[@id=\"login\"]" ) ).sendKeys( "deank06" );
            driver.findElement( By.xpath( "//*[@id=\"login_password\"]" ) ).sendKeys( "enteronly1" );
            driver.findElement( By.xpath( "//*[@id=\"login_button\"]" ) ).click();
        }

    }
}
