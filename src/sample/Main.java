package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import sample.controller.ViewController;

import java.io.IOException;

public class Main extends Application {
/*
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Morse Code Translator");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();

    }


    public static void main(String[] args) {

        launch(args);
    }

    */


    //PrimaryStage
    private Stage primaryStage;

    //BorderPane of RootLayout
    private BorderPane rootLayout;
    public static Scene scene;
    // private VBox rootLayout;
    // private AnchorPane rootLayout;

    @Override
    public void start(Stage primaryStage) {
        //Declare a primary stage (will be used for everything)
        this.primaryStage = primaryStage;

        //Set primary stage title
        this.primaryStage.setTitle("Morse Code Translator");
        //this.primaryStage.setScene(new Scene(scene, 600, 400));

        //Initialize RootLayout
        //initRootLayout();

        //Display the Start View
        showStartView();

        //Display the View
        // String location = "view/StartView.fxml";
        //showView(location);
    }
/*
    //Initializes the root layout.
    public void initRootLayout() {
        try {
            //Load root layout from RootLayout.fxml
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();
            //  rootLayout = (VBox) loader.load();
            // rootLayout = (AnchorPane) loader.load();


            //Show the scene holding the root layout.
            scene = new Scene(rootLayout); //rootLayout sent to scene.
            primaryStage.setScene(scene); //Set the scene in primary stage.
*/

            /*//Give the controller access to the main.
            RootLayoutController controller = loader.getController();
            controller.setMain(this);*/
/*
            //Show the primary stage
            primaryStage.show(); //Display the primary stage
        } catch (IOException e) {
            System.err.println("An IO exception occured when initilizing root layout");
            e.printStackTrace();
        }
    }


*/
    //Shows the start view inside the root layout.

    public void showStartView() {


        try {
            //Load StartView from StartView.fxml
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("views/StartView.fxml"));
            //loader.setLocation(Main.class.getResource("view/AreaView.fxml"));
            //BorderPane startView = (BorderPane) loader.load();
            AnchorPane startView = (AnchorPane) loader.load();

            //Create an instance of ViewController to hold the different controller. We need Scene which is the "foundation" of the view
            ViewController viewcontroller = new ViewController(scene);
            //Add all the controller we want in our project here
            //viewcontroller.addScreen("RequestView", FXMLLoader.load(getClass().getResource( "view/RequestViewLayout.fxml" ))); //Name + location


            //ViewControllers(scene);
            // Set Start view into the center of root layout.
            rootLayout.setCenter(startView);
            //rootLayout.set(startView);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
