package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;
import selenium.GetTeams;
import selenium.Login;
import storage.Teams;

@SuppressWarnings("restriction")
public class Main extends Application
{

    GetTeams getTeams = new GetTeams();
    VBox layout1;
    HBox hbox;
    Scene mainScene, teamScene;


    public static void main( String[] args )
    {
        launch( args );
    }


    @Override
    public void start( Stage primaryStage ) throws Exception
    {        
        Button loginButton = new Button( "Login" );
        layout1 = new VBox( 20 );
        loginButton.setOnAction( e -> {
            Login login = new Login();
            try
            {
                login.loginUser();
            }
            catch( Exception e1 )
            {
                e1.printStackTrace();
            }
            loginButton.setText( login.getUser() );
            loginButton.setDisable( true );
        } );

        Button getTeamsButton = new Button( "Load Teams and Details" );
        getTeamsButton.setOnAction( e -> {
            getTeams.scrapeTeams();


            for( Teams team : getTeams.getTeams() )
            {
                Button teamName = new Button( team.getTeamName());
                layout1.getChildren().add( teamName );                
                teamName.setOnAction(evt -> {              
                    
                    for (String player : team.getRoster()) {
                        CheckBox players = new CheckBox(player);
                        layout1.getChildren().add( players );
                    }
                    
                    Button getGb = new Button("GET GB");
                    layout1.getChildren().add( getGb );
                    getGb.setOnAction( g -> {
                        
                    });
                    
                });

            }

        } );

        // Layout 1
        layout1.getChildren().addAll( loginButton, getTeamsButton );
        mainScene = new Scene( layout1, 200, 600 );

        primaryStage.setScene( mainScene );
        primaryStage.setTitle( "GB Getter" );
        primaryStage.show();

    }

}