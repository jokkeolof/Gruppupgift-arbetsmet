package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    private TextField MorseIN;
    @FXML
    private Label MorseOUT;
    @FXML
    private Button Translate;
    @FXML
    private Button PlaySound;



MorseTranslator MT = new MorseTranslator();

MorseCodeGenerator MCG = new MorseCodeGenerator();

@FXML
private void translatebuttonpush(){
String str = MorseIN.getText();
MT.toStringArray(str);
MorseOUT.setText(MT.morseOutput.toString());
}
@FXML
    private void playsoundbuttonpush(){
String str =MorseIN.getText();
MCG.playSound(str);

}
}
