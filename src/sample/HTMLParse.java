package sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

/*

Ett försök att använda sig av unv.is för att kunna direkt översätta artiklar eller andra hemsidor direkt från en URL

 */
public class HTMLParse {

    public static void main(String[] args) {

        URL url;
        String BASE_URL = "https://unv.is/";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter url");
        String input = scanner.nextLine();

        try {
            // get URL content

            String a= BASE_URL + "https://www." + input;
            url = new URL(a);
            URLConnection conn = url.openConnection();

            // open the stream and put it into BufferedReader
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));

            String inputLine;
            while ((inputLine = br.readLine()) != null) {
                System.out.println(inputLine);
            }
            br.close();

            System.out.println("Done");

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }




    }
}
