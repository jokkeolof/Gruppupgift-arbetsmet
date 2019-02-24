package sample;

import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Controller {

    @FXML private TextField MorseIN;
    @FXML private TextArea MorseOUTTA;
    @FXML private Button Translate;
    @FXML private Button PlaySound;
    @FXML private Button Stopound;
    @FXML private CheckBox soundCheckBox;
    @FXML private MenuItem CloseItem;
    @FXML private MenuItem AboutItem;

    private ArrayList<String> translation = new ArrayList<>();


MorseTranslator MT = new MorseTranslator();
MorseCodeGenerator MCG = new MorseCodeGenerator();


    /**
     * event som startar när translate knappen trycks på i GUI´t. Metoden tar den inmatade strängen och översätter den
     * samt visar morsekdoen. Om "with sound" är bockat är det även med ljud.
     * If-sats styr om det är en URL från aftonbladet.
     */
    @FXML
private void translatebuttonpush(){

    MorseOUTTA.setText("");    //Börja med att reseta texten
	String str = MorseIN.getText();

 	// Setting translation to textfield
    // Check if we are looking at a URL or if it's plain text. If URL grab the url and get the domain and return string of site. If plain text grab the text and run it thorugh the translator.
    if(str.contains("https://www.aftonbladet.se/")) {
        if(str.contains("https://www.aftonbladet.se/tv")) {
            MorseOUTTA.setText("Vi kan inte hantera aftonbladets TV sidor");
            return;
        }
       // MorseOUTTA.setText("Hämtar artikel..");
        GEThtml get = new GEThtml();
        String ReturnedData = get.GETArticleFromURL(str);
        System.out.println(ReturnedData);
        if(ReturnedData.equals(str)) { //Ibland kan det vara så att endast samma sträng retuneras(troligen pga unvis.it) Detta hanteras då så an får försöka igen
            MorseOUTTA.setText("Det gick inte att ansluta till servern.. Det kanske är för många förfrågningar just nu, försök igen om några sekunder");
        }else {
            MorseOUTTA.setText(MT.translateToString(ReturnedData));
        }
    }else{
        MorseOUTTA.setText(MT.translateToString(str));
        }
    if (soundCheckBox.isSelected()){
    playsoundbuttonpush();
}  }

    /**
     * stop funktion för ljudet. Enda sättet att få ljudet att sluta annars är att starta om applikationen.
     * Ljudet fortsätter när den är stängd.
     */
    public void stop() {
    MCG.stopSound();
}

    /**
     * De tre metoderna nedanför är till menyn som finns högst upp i programmets main sida.
     */
    public void menubarCLOSE(){
    System.exit(0);
}

    public void menubarABOUT(){
    String str = "This program translates text to morsecode with sound!";
    MorseIN.setText(str);
    translatebuttonpush();
    playsoundbuttonpush();
}
    public void menubarABOUTUS() {
    String str = "This program is made as a teamwork project in class:\nArbetsmetodik för utvecklare - Systemintegratör 2018/19\nMade by:\nAnders Nilsson\nDennis Larsson\nJoakim Olofsson\nNicklas Holmberg";
    MorseOUTTA.setText(str);
}

    /**
     * För att starta ljudet anropar den funktioner från MorseCodeGenerator classen
     */
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

    /**
     * De två metoderna nedanför är till för att byta scener. En till att öppna ett txt dokument och den andra till URL.
     */
    public void changesceneTOFILE(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("ReadFile.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);


        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }
    public void changesceneTOURL(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("ReadURL.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);


        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }
}

