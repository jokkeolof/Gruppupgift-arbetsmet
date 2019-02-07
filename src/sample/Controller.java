package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {

    @FXML private TextField MorseIN;
    @FXML private Label MorseOUT;
    @FXML private TextArea MorseOUTTA;
    @FXML private Button Translate;
    @FXML private Button PlaySound;
    @FXML private Button PlaySound2;
    @FXML private CheckBox soundCheckBox;
    @FXML private MenuItem CloseItem;
    @FXML private MenuItem AboutItem;


MorseTranslator MT = new MorseTranslator();

MorseCodeGenerator MCG = new MorseCodeGenerator();
Main M = new Main();

@FXML
private void translatebuttonpush(){
String str = MorseIN.getText();
MorseOUTTA.setText(MT.toArrayList(str).toString());

if (soundCheckBox.isSelected()){
    playsoundbuttonpush();
}
}

public void menubarCLOSE(){

}
public void menubarABOUT(){
    String str = "this program translates to morsecode with sound!";
    MorseIN.setText(str);
    translatebuttonpush();
    playsoundbuttonpush();
}
    @FXML
    private void playsoundbuttonpush(){
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

    public void changesceneTOFILE(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("ReadFile.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);


        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }
}

