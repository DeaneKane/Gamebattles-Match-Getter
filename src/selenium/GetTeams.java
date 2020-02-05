package selenium;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class GetTeams extends StartFirefoxDriver
{
    ArrayList<String> teams = new ArrayList<>();
    ArrayList<String> roster = new ArrayList<>();
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
        rosterMembers = driver.findElementsByClassName( "member-username" );
        for( WebElement languageElement : rosterMembers )
        {
            roster.add( languageElement.getText() );
            System.out.println(languageElement.getText());
        }

    }


    public List<String> getTeams()
    {
        return teams;
    }
}
