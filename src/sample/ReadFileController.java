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

// ärver från Controller klassen
public class ReadFileController extends  Controller{

    @FXML private Button file;
    @FXML private TextArea textareaIN;
    @FXML private TextArea textareaUT;
    @FXML private CheckBox SoundCheckBox;
    @FXML private Button StopSound;

    Controller cont = new Controller();

    public void stop() {
        MCG.stopSound();
    }

    /**
     * här behövdes två olika strängar för att visa inlästa texten från dokumenetet och för att visa morsekoden.
     * den ena strängen beöver föja originaldokumentet med ny rad medans morsekoden ej behöver det.
     */
    String str2MorseCode =  "";
String str2show = "";

    /**
     *Metod för att ladda in ett TXT dokument som man kan översätta om så önskas.
     */
    public void loadfile2(ActionEvent event) {
    textareaUT.setText("");
    textareaIN.setText("");
        FileChooser fc = new FileChooser();
             fc.getExtensionFilters()
            .addAll( //Styr till bara txt eller TXT filer är valbara
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

                br.close(); //stänger filen

            } catch (IOException ex) {
                ex.printStackTrace();
            }
}
    //se samma metod i Controller, denna är väldigt avskalad
    public void translatebuttonpush(){
        String str2 = textareaIN.getText();
        textareaUT.setText(MT.toArrayList(str2).toString());
        if (SoundCheckBox.isSelected()){
            playsoundbuttonpush();
        }
    }

    //se samma metod i Controller
    public void playsoundbuttonpush(){
        cont.playsoundbuttonpush();
    }
    // Metod för att gå tillbaka till Main scenen
    public void changesceneTOMAIN(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);


        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }
}
