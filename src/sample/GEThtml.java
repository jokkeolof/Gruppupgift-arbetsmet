package sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class GEThtml {

    public static String GETArticleFromURL(String url) {
        //GEThtml get = new GEThtml();
        List<String> FromURLAsArray = parse(url);
        List<String> wordList = Arrays.asList(ParseConfig.DATA); // RemovableWords listan görs om till en String list
        List<String> Output = filter(FromURLAsArray, wordList);
        String test = Output.get(96);
        String replaced = test.replaceAll(("(<div>|<\\/p>|<p>|<span class=\"article-link\" data-article-link-href=\"|<\\/span>|<\\/div>|<picture>|<source/>|</picture>|<time>)|</time>|//aftonbladet.se/tagg/"), " ");
        //System.out.println(replaced);
        //System.out.println(Output.get(96));
        return replaced;
    }

    public static List<String> parse(String inurl) {
        System.setProperty("https.protocols", "TLSv1.2,TLSv1.1,SSLv3");
        System.setProperty("http.agent", "Chrome");
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
        return myList;
    }



    public static List<String> filter(List<String> FromUrl, List<String> RemovableWords) {

        FromUrl.removeAll(new HashSet(RemovableWords));

        return FromUrl;
    }





}

