package controller;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import selenium.GetTeams;
import selenium.Login;

@SuppressWarnings("restriction")
public class Main extends Application
{
    public static void main( String[] args )
    {   
        launch(args);
    }

    @Override
    public void start( Stage primaryStage ) throws Exception
    {
        Scene mainScene;
        Button loginButton = new Button( "Login" );
        loginButton.setOnAction( e -> {
            Login login = new Login();
            login.loginUser();
            loginButton.setText( login.getUser() );
            loginButton.setDisable(true);            
        } );
        
        Button getTeamsButton = new Button( "Load Teams and Details" );
        getTeamsButton.setOnAction( e -> {
            GetTeams getTeams = new GetTeams();
            getTeams.scrapeTeams();
            
        });
        
        // Layout 1
        VBox layout1 = new VBox( 20 );
        layout1.getChildren().addAll(  loginButton, getTeamsButton );
        mainScene = new Scene( layout1, 200, 200 );

        primaryStage.setScene( mainScene );
        primaryStage.setTitle( "GB Getter" );
        primaryStage.show();
        
    }
}