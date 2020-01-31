package controller;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
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
        Button button1 = new Button( "Login" );
        button1.setOnAction( e -> {
            Login login = new Login();
            login.loginUser();
            
        } );

        // Layout 1
        VBox layout1 = new VBox( 20 );
        layout1.getChildren().add(  button1 );
        mainScene = new Scene( layout1, 200, 200 );

        primaryStage.setScene( mainScene );
        primaryStage.setTitle( "GB Getter" );
        primaryStage.show();
        
    }
}