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
import javafx.scene.control.ListView;
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
    @FXML private ListView listviewIN;
    @FXML private CheckBox SoundCheckBox;

    MorseTranslator MT2 = new MorseTranslator();
    MorseCodeGenerator MCG2 = new MorseCodeGenerator();
    Controller C = new Controller();

public void loadfile(){
    EventHandler<ActionEvent> btnLoadEventListner = (ActionEvent event) -> {
        FileChooser ChooseFile = new FileChooser();
        ChooseFile.getExtensionFilters()
                .addAll(
                        new FileChooser.ExtensionFilter("TXT files (*.TXT)", "*.TXT"),
                        new FileChooser.ExtensionFilter("txt files (*.txt)", "*.txt"));

        File file = ChooseFile.showOpenDialog(null);
        if (file != null) {

                     //   listviewIN.getItems().add(ChooseFile.toString());
        }
    };  }
    String str = "";
public void loadfile2(ActionEvent event) {
        FileChooser fc = new FileChooser();
        File loadedfile = fc.showOpenDialog(null);

             try {
                FileReader fr = new FileReader(loadedfile.getPath());
                BufferedReader br = new BufferedReader(fr);
                String sCurrentLine;

                while ((sCurrentLine = br.readLine()) !=null) {
                str += sCurrentLine + "\n";
                    textareaIN.setText(str);
                }

                br.close();

            } catch (IOException ex) {
                ex.printStackTrace();
            }
}

    public void translatebuttonpush(){
        String str2 = textareaIN.getText();
        textareaUT.setText(MT2.toArrayList(str).toString());
        if (SoundCheckBox.isSelected()){
            playsoundbuttonpush();
        }
    }
    private void playsoundbuttonpush(){
       // String str = textareaIN.getText();
        Thread thread = new Thread() {
            @Override
            public void run() {
                MCG2.startSound();
                MCG2.playSound(MT2.toStringArray(str));
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
