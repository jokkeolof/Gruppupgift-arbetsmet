package sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class GETHtml {

    public static void main(String[] args) {
       GETHtml get = new GETHtml();
       List<String> FromURLAsArray = get.parse("https://www.aftonbladet.se/nyheter/kolumnister/a/P3bLnR/jan-bjorklund-avgick-i-panik");
        List<String> wordList = Arrays.asList(ParseConfig.DATA);
        List<String> Output = filter(FromURLAsArray, wordList);

        System.out.println("WORDLIST " + wordList.toString());
       System.out.println(Output.toString());
       /*for(String data : ParseConfig.DATA) {
           System.out.println(data);
        }*/
    }

    public List<String> parse(String inurl) {
       System.setProperty("https.protocols", "TLSv1.2,TLSv1.1,SSLv3");
        String content = "";
        StringBuilder sb = null;
        BufferedReader br = null;
        List<String> myList = new ArrayList<String>();
        String buildurl = "https://unv.is/" + inurl;
        //Denna kan endast parsea aftonbladet så vi måste kolla så det blir rätt URL direkt
        if(inurl.contains("https://www.aftonbladet.se/")) {


            try {

                URL url = new URL(buildurl);
                br = new BufferedReader(new InputStreamReader(url.openStream()));

                String line;

                //sb = new StringBuilder();

                while ((line = br.readLine()) != null) {

                  //  sb.append(line);
                   // sb.append(System.lineSeparator());
                    myList.add(line);
                }

               // System.out.println(sb);
            } catch(MalformedURLException mal) {
                System.out.println("ERROR" + mal);
            }catch (IOException io) {
                System.out.println("ERROR" + io);
            }
                finally {


                if (br != null) {
                    try {
                    br.close();
                    }catch (IOException io) {
                        System.out.println("EEOR" + io);
                    }
                }
            }



        }

       // StringBuilder finishedString = ActiveParse(sb);
        //System.out.println(" THIS IS THE ARRAYLIST " + myList.toString());
      return myList;
        //return sb.toString();
    }



        public static List<String> filter(List<String> FromUrl, List<String> RemovableWords) {
            List<String> uniq = new ArrayList<String>(FromUrl);
            FromUrl.forEach(elem -> uniq.removeIf(x -> x.equals(RemovableWords)));
            return uniq;
        }






/*


    public Set<String> getUnique(List<String> FromURL, List<String> RemovableWords) {
        HashSet<String> result = new HashSet<String>(); //Resultatet

        boolean contains = false;

        for (String s : FromURL) {
            for (String unique : RemovableWords) {
                if (unique.contains(s)) {
                    contains = true;
                    break;
                } else if (s.contains(unique)) {
                    result.remove(unique);
                    result.add(s);
                    contains = true;
                    break;
                }
            }
            if (!contains) {
                result.add(s);
            }
        }

        return result;

    }





*/








/*

    public static StringBuilder ActiveParse(StringBuilder in) {
        String finishedString;
        String[] CheckIfIncluded = ParseConfig.DATA;
        for(finishedString : in) {

        }

        return finishedString;
    }



    //Metoden kollar inputen och gör omvandlingen
    public static String checkInput(String userInput) {
        int indexInput = 0;//För att hålla reda på vilken bokstav vi står på i strängen för Inputen
        int indexKons = 0;//För att hålla reda på vilken bokstav vi står på i strängen för konsonanterna
        String buildString = ""; // Vi bygger en ny sträng beroende på resultatet och retunerar sedan denna nedan
        String kons = "bcdfghjklmnpqrstvwxz"; // Alla konsonanter
        boolean gotMatch = false; //Kontrollerar om vi får en matching eller inte

        //For loop på användarens input
        for (int i = 0; i < userInput.length(); i++) {
            gotMatch = false; //Börjar med att reseta "om vi fått match eller ej"

            //Första delen kollar om vi får en matching med användarens input 0 mot konsonanternas position 0
            if(userInput.charAt(indexInput) == kons.charAt(indexKons)) {
                buildString = buildString + userInput.charAt(indexInput) + 'o' + kons.charAt(indexKons);
                gotMatch = true;
            }

            else if(!gotMatch) { //Om ingen matchning, loopar vi igenom kons strängen för att försöka hitta en match, fast vi står kvar på samma position i användarens input sträng (0 första rundan)
                for(int a = 0; a < kons.length(); a++) { //for loop för konsonanter strängen
                    if(userInput.charAt(indexInput) == kons.charAt(indexKons)) { //Vi kollar av mot kons strängen, och plussar då på indexKons fast användarens input står kvar på samma plats
                        buildString = buildString + userInput.charAt(indexInput) + 'o' + kons.charAt(indexKons);
                        indexKons = 0; //Om vi träffar rätt, reseta kontrollvariablerna och avsluta loopen
                        indexInput++;
                        gotMatch = true;
                        break;
                    } else {
                        indexKons++;
                    }
                }
            }
            //Får vi absolut ingen matching så lägger vi bara till värdet i buildString, sedan plussar vi på användarens input för att kolla nästa position.
            if(!gotMatch){

                buildString = buildString + userInput.charAt(indexInput);
                indexKons = 0;
                indexInput++;
            }
        }


*/

}
