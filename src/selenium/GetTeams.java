package selenium;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GetTeams extends StartFirefoxDriver
{
    ArrayList<String> teams = new ArrayList<>();
    ArrayList<String> roster = new ArrayList<>();
    ArrayList<String> teamIds = new ArrayList<>();
    List<WebElement> teamElements;
    List<WebElement> rosterMembers;


    // Constructer
    public GetTeams()
    {
        // Constructer
    }


    public List<String> scrapeTeams()
    {

        driver.navigate().to( "https://gamebattles.majorleaguegaming.com/my-competitions" );
        teamElements = driver.findElementsByClassName( "team-name" );
        for( WebElement languageElement : teamElements )
        {
            teams.add( languageElement.getText() );
            scrapeEachTeamDetails( languageElement );
        }

        return teams;
    }


    public void scrapeEachTeamDetails( WebElement langElement )
    {

        langElement.click();
        new WebDriverWait(driver, 20).until(ExpectedConditions.urlContains("team"));
        String currentUrl = driver.getCurrentUrl();
        teamIds.add( currentUrl.substring( 69 ));
        rosterMembers = driver.findElementsByClassName( "member-username" );
        for( WebElement languageElement : rosterMembers )
        {
            roster.add( languageElement.getText() );
        }

    }


    public List<String> getTeams()
    {
        return teams;
    }
}
