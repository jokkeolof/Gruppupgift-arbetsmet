package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ReadURLController extends Controller{

    @FXML private TextField MorseIN;
    @FXML private TextArea MorseOUTTA;
    @FXML private CheckBox soundCheckBox;

    public void stop() {
        MCG.stopSound();    }

    private void translatebuttonpush(){

        MorseOUTTA.setText("");    //Börja med att reseta texten
        String str = MorseIN.getText();

        // Setting translation to textfield
        // Check if we are looking at a URL or if it's plain text. If URL grab the url and get the domain and return string of site. If plain text grab the text and run it thorugh the translator.
        if(str.contains("https://www.aftonbladet.se/")) {
            GEThtml get = new GEThtml();
            String ReturnedData = get.GETArticleFromURL(str);
            MorseOUTTA.setText(MT.translateToString(ReturnedData));
        }else{
            MorseOUTTA.setText(MT.translateToString(str));
        }
        if (soundCheckBox.isSelected()){
            playsoundbuttonpush();
        }  }

    @FXML
    public void playsoundbuttonpush(){
        String str = MorseIN.getText();
        Thread thread = new Thread() {
            @Override
            public void run() {
                MCG.startSound();
                MCG.playSound(MT.toStringArray(str));
            }
        };
        thread.start();
    }

    // Metod för att gå tillbaka till main scenen.
    public void changesceneTOMAIN(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);


        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }
}

