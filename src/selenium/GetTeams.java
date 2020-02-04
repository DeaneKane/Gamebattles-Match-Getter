package selenium;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.WebElement;

public class GetTeams extends StartFirefoxDriver
{
    ArrayList<String> teams = new ArrayList<>();

    List<WebElement> teamElements;

    //Constructer
    public GetTeams()
    {
        //Constructer
    }


    public List<String> scrapeTeams(){
                
        driver.navigate().to("https://gamebattles.majorleaguegaming.com/my-competitions");
        teamElements = driver.findElementsByClassName( "team-name" );        
        for(WebElement languageElement : teamElements) {
            teams.add( languageElement.getText() );           
        }
               
        return teams;
    }

    public List<String> getTeams()
    {
        return teams;
    }
}
