package storage;

import java.util.ArrayList;

public class Teams
{
    private String teamName;
    private String teamId;
    private ArrayList<String> roster;
    
    public String getTeamName()
    {
        return teamName;
    }
    public void setTeamName( String teamName )
    {
        this.teamName = teamName;
    }
    public String getTeamId()
    {
        return teamId;
    }
    public void setTeamId( String teamId )
    {
        this.teamId = teamId;
    }
    public ArrayList<String> getRoster()
    {
        return roster;
    }
    public void setRoster( ArrayList<String> roster )
    {
        this.roster = roster;
    }
}
