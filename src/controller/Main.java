package controller;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;

public class Main extends Application // implements EventHandler<ActionEvent>
{
    @SuppressWarnings("restriction")
    Stage window;
    Scene scene1, scene2;


    public static void main( String[] args )
    {
        System.setProperty( "webdriver.chrome.driver", "C:\\SeleniumGecko\\chromedriver.exe" );
        launch( args );

    }


    @Override
    public void start( Stage primaryStage ) throws Exception
    {
        window = primaryStage;

        Label label1 = new Label( "Scene 1" );
        Button button1 = new Button( "Go to scene 2" );
        button1.setOnAction( e -> {
            WebDriver driver = new ChromeDriver();
            driver.get( "https://gamebattles.com" );
            driver.manage().window().maximize();
            if( driver.findElement( By.xpath( "//*[@id=\"cdk-overlay-0\"]/gb-privacy-policy-updated-dialog/div/gb-card/div[2]/button/span/div/div" ) ) != null )
            {
                driver.findElement( By.xpath( "//*[@id=\"cdk-overlay-0\"]/gb-privacy-policy-updated-dialog/div/gb-card/div[2]/button/span/div/div" ) ).click();
            }
        } );

        // Layout 1
        VBox layout1 = new VBox( 20 );
        layout1.getChildren().addAll( label1, button1 );
        scene1 = new Scene( layout1, 200, 200 );

        // //Button 2
        // Button button2 = new Button("Go to scene 1");
        // button1.setOnAction( e -> window.setScene(scene1) );
        //
        // //Layout 2
        // StackPane layout2 = new StackPane();
        // layout2.getChildren().add( button2 );
        // scene2 = new Scene(layout2, 600, 200);

        window.setScene( scene1 );
        window.setTitle( "title" );
        window.show();
    }

}
