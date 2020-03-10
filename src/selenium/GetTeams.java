package selenium;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import storage.Teams;

public class GetTeams extends StartFirefoxDriver
{
    ArrayList<String> roster = new ArrayList<>();
    ArrayList<Teams> teams = new ArrayList<>();
    List<WebElement> rosterMembers;
    Teams team;

    // Constructer
    public GetTeams()
    {
        // Constructer
    }


    public List<Teams> scrapeTeams()
    {

        driver.navigate().to( "https://gamebattles.majorleaguegaming.com/my-competitions" );
        WebDriverWait wait = new WebDriverWait(driver,10);
        List<WebElement> teamElements = driver.findElementsByClassName( "team-name" );
        int size = teamElements.size();
        
        for( int i = 0; i < size; i++ )
        {
            team = new Teams();
            wait.until(ExpectedConditions.elementToBeClickable((By.className("team-name"))));
            List<WebElement> teamElementsInLoop = driver.findElementsByClassName( "team-name" );
            size = teamElementsInLoop.size();         
            team.setTeamName( teamElementsInLoop.get(i).getText() );
            scrapeEachTeamDetails( teamElementsInLoop.get( i ) );
            driver.navigate().back();
        }
        return teams;
    }


    public void scrapeEachTeamDetails( WebElement langElement )
    {

        langElement.click();
        new WebDriverWait( driver, 20 ).until( ExpectedConditions.urlContains( "team" ) );
        String currentUrl = driver.getCurrentUrl();
        team.setTeamId( currentUrl.substring( 69 ) );
        rosterMembers = driver.findElementsByClassName( "member-username" );
        for( WebElement languageElement : rosterMembers )
        {
            roster.add( languageElement.getText() );
        }
        team.setRoster( roster );
        teams.add( team );
        roster.clear();
    }

    public List<Teams> getTeams()
    {
        return teams;
    }  
}
