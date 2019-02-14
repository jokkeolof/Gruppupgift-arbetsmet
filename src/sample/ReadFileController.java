package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;

import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;

public class ReadFileController extends  Controller{

    @FXML private Button file;
    @FXML private TextArea textareaIN;
    @FXML private TextArea textareaUT;
    @FXML private CheckBox SoundCheckBox;
    @FXML private Button StopSound;

    MorseTranslator MT2 = new MorseTranslator();
    MorseCodeGenerator MCG2 = new MorseCodeGenerator();

    public void stop() {
        MCG2.stopSound();
    }

String str2MorseCode =  "";
String str2show = "";
public void loadfile2(ActionEvent event) {
    textareaUT.setText("");
    textareaIN.setText("");
        FileChooser fc = new FileChooser();
             fc.getExtensionFilters()
            .addAll(
                    new FileChooser.ExtensionFilter("TXT files (*.TXT)", "*.TXT"),
                    new FileChooser.ExtensionFilter("txt files (*.txt)", "*.txt"));

    File loadedfile = fc.showOpenDialog(null);

             try {
                FileReader fr = new FileReader(loadedfile.getPath());
                BufferedReader br = new BufferedReader(fr);
                String sCurrentLine;

                while ((sCurrentLine = br.readLine()) !=null) {
                    str2show += sCurrentLine +"\n";
                    str2MorseCode += sCurrentLine;
                    textareaIN.setText(str2show);
                }

                br.close();

            } catch (IOException ex) {
                ex.printStackTrace();
            }
}

    public void translatebuttonpush(){
        String str2 = textareaIN.getText();
        textareaUT.setText(MT2.toArrayList(str2).toString());
        if (SoundCheckBox.isSelected()){
            playsoundbuttonpush();
        }
    }
    private void playsoundbuttonpush(){
        Thread thread = new Thread() {
            @Override
            public void run() {
                MCG2.startSound();
                MCG2.playSound(MT2.toStringArray(str2MorseCode));
            }
        };
        thread.start();
    }
    // Method to change back to scene 1
    public void changesceneTOMAIN(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);


        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }
}
