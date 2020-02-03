package selenium;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class GetTeams extends StartFirefoxDriver
{
    ArrayList<String> teams = new ArrayList<>();

    public GetTeams()
    {

    }


    public ArrayList<String> scrapeTeams(){
        
        Actions action = new Actions(driver);
        WebElement teamsHover = driver.findElement( By.xpath( "/html/body/gb-root/gb-main-layout/div/div/sh-header-container/sh-header/div/div/div[2]/sh-submenu-trigger[1]/div/button/sh-teams-icon/img" ) );
        action.moveToElement( teamsHover );
        boolean mt;
        int i = 1;
        do {           
                        
            mt = moreTeams(i);
            System.out.println(driver.findElement( By.xpath( "/html/body/gb-root/gb-main-layout/div/div/sh-header-container/sh-header/div/div/div[2]/sh-submenu-trigger[1]/div/div/sh-header-sub-menu/div/div/div/sh-teams-component/div/a["
                    + i
                    + "]/div/div[1]")).getText());
            teams.add( driver.findElement( By.xpath( "/html/body/gb-root/gb-main-layout/div/div/sh-header-container/sh-header/div/div/div[2]/sh-submenu-trigger[1]/div/div/sh-header-sub-menu/div/div/div/sh-teams-component/div/a["
                                          + i
                                          + "]/div/div[1]" )).getText());
            i++;

                                  
        } while(mt);
        
        
        return teams;
        
    }


    public boolean moreTeams( int i )
    {

        if( driver.findElement( By.xpath( "/html/body/gb-root/gb-main-layout/div/div/sh-header-container/sh-header/div/div/div[2]/sh-submenu-trigger[1]/div/div/sh-header-sub-menu/div/div/div/sh-teams-component/div/a["
                                          + i
                                          + "]/div/div[1]" ) ) != null )
        {
            
            return true;
        }
        else
        {

            return false;
        }
    }

}
