package selenium;

import java.io.File;

import org.openqa.selenium.By;

import storage.LoginDecryption;
import storage.LoginEncryption;

public class Login extends StartFirefoxDriver
{
    private String user;
    private String password;
    private File detailsFile = new File( "configurations.des" );

    public Login()
    {

        // Start Firefox driver with saved settings
        driver = startFirefox();
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
        return driver.findElement( By.xpath( "/html/body/gb-root/gb-main-layout/div/div/sh-header-container/sh-header/div/div/div[2]/button[1]" ) ) == null;
    }

    public void loginUser() throws Exception
    {

//        checkForPrivacyPrompt();

        if( !checkIfAlreadyLoggedIn() )
        {
            driver.findElement( By.xpath( "/html/body/gb-root/gb-main-layout/div/div/sh-header-container/sh-header/div/div/div[2]/button[1]" ) )
                  .click();  
            
            if(!checkIfDetailsStored()) {
                LoginEncryption le = new LoginEncryption();
                le.deletePlainTextFile();
            }
            
            LoginDecryption ld = new LoginDecryption();
            user = ld.extractUsername();
            password = ld.extractPassword();
            ld.deletePlainTextFile();
            
            driver.findElement( By.xpath( "//*[@id=\"login\"]" ) ).sendKeys( user );
            driver.findElement( By.xpath( "//*[@id=\"login_password\"]" ) ).sendKeys( password );
            driver.findElement( By.xpath( "//*[@id=\"login_button\"]" ) ).click();       
            scrapeUsername();
        }        
    }
    
    public String scrapeUsername() {
               
        String userScraped = driver.findElement( By.xpath( "/html/body/gb-root/gb-main-layout/div/div/sh-header-container/sh-header/div/div/div[2]/sh-submenu-trigger[2]/div/button" ) ).getText();
        user = userScraped.substring( 0 , (userScraped.length() -2 )) ;

        return user;       
    }
    
    public String getUser()
    {
        return user;
    }
    
    public boolean checkIfDetailsStored()
    {
        return detailsFile.exists();
    }

}
